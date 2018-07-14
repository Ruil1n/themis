package dangod.themis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Component
public class ReplayDefender {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${dev-mode}")
    private boolean dev;

    public Boolean checkReplay(long timestamp, String nonce){
        if(dev) //生产环境不进行重放防御
            return false;

        long now = Calendar.getInstance().getTime().getTime();
        if(now - timestamp > 60000) {
            logger.warn("疑似重放攻击: 过时请求");
            return true;
        }
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory)redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(1);
        String value = (String)redisTemplate.boundValueOps(nonce).get();
        if(value != null){
            logger.warn("疑似重放攻击: 随机数相同");
            return true;
        }
        else
            redisTemplate.boundValueOps(nonce).set(nonce, 60L, TimeUnit.SECONDS);
        return false;
    }

}

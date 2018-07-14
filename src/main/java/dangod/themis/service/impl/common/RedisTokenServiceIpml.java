package dangod.themis.service.impl.common;

import dangod.themis.model.vo.TokenVo;
import dangod.themis.service.common.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTokenServiceIpml implements TokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String createToken(long userId) {
        String uuid = UUID.randomUUID().toString().replace("-","");
        TokenVo tokenVo = new TokenVo(userId, uuid);

        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory)redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(0);
        ValueOperations<String, String> operations=redisTemplate.opsForValue();
        operations.set(String.valueOf(userId), uuid, 30L, TimeUnit.MINUTES);
        return tokenVo.toString();
    }

    @Override
    public boolean checkToken(String authentication) {
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory)redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(0);
        try {
            TokenVo tokenVo = new TokenVo(authentication);
            String uuid = (String)redisTemplate.boundValueOps(String.valueOf(tokenVo.getUserId())).get();
            if (uuid == null || !tokenVo.getToken().equals(uuid))
                return false;
            else
                redisTemplate.boundValueOps(String.valueOf(tokenVo.getUserId())).expire(30L, TimeUnit.MINUTES);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteToken(long userId) {
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory)redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(0);
        redisTemplate.delete(String.valueOf(userId));
    }

    @Override
    public void deleteToken(String authentication) {
        try {
            TokenVo tokenVo = new TokenVo(authentication);
            deleteToken(tokenVo.getUserId());
        }catch (Exception e){
            return ;
        }
    }
}

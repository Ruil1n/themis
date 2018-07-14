package dangod.themis;

import com.alibaba.fastjson.JSON;

/**
 * Created by Ruilin on 2018/7/9.
 */
public class Test {
    @org.junit.Test
    public void test(){
        System.out.println(JSON.parseArray("[1,2,3,4]", Long.class).toString());
    }
}

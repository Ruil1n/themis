package dangod.themis;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * Created by Ruilin on 2018/7/9.
 */
public class Test {
    @org.junit.Test
    public void test() throws IOException {
        System.out.println(JSON.parseArray("[1,2,3,4]", Long.class).toString());
        File file = new File("/../../../../../../../../../../../../../../../../../../../etc/passwd");
        InputStreamReader reader=new InputStreamReader(new FileInputStream(file),"GBK");
        BufferedReader bfreader=new BufferedReader(reader);
        String line;
        while((line=bfreader.readLine())!=null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
            System.out.println(line);
        }

    }
}

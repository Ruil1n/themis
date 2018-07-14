package dangod.themis.model.vo;


import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TokenVo {
    private long userId;
    private String token;

    public TokenVo(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public TokenVo(String token) {
        byte[] bytes = Base64.getDecoder().decode(token);
        String[] arr = new String[0];
        try {
            arr = new String(bytes,"utf-8").split("-");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.userId = Long.parseLong(arr[0]);
        this.token = arr[1];
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString(){
        try {
            return Base64.getEncoder().encodeToString((userId+"-"+token).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

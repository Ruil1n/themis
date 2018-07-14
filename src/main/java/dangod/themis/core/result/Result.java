package dangod.themis.core.result;

import com.alibaba.fastjson.JSON;

public class Result {
    private int status;
    private Object data;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static String send(int status, Object data, String message) {
        return new Result(status, data, message).toString();
    }
}

package dangod.themis.controller.base.constant;

public class Status {
    //基础返回码
    public final static Integer SUCCESS = 200;
    public final static Integer FAIL = 400;
    public final static Integer UNAUTHORIZED = 401;//未认证（令牌错误）
    public final static Integer PERMISSIN_DENIED = 402;//无权限（令牌错误）

    public final static Integer NOT_FIND = 404;
    public final static Integer SERVER_ERROR = 500;

    //特殊返回码
    public final static Integer REPLAY_ATTACK = 601;//疑似重放攻击返回码

}

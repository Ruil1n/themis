package dangod.themis.model.vo;

import dangod.themis.model.po.common.UserBaseInfo;

public class UserBaseInfoVo {
    private long id;
    private String realName;
    private String email;
    private String sex;

    public UserBaseInfoVo() {
    }

    public UserBaseInfoVo(UserBaseInfo baseInfo) {
        this.id = baseInfo.getUser().getId();
        this.realName = baseInfo.getRealName();
        this.email = baseInfo.getEmail();
        this.sex = baseInfo.getSex();
    }

    public UserBaseInfoVo(long id, String realName, String email, String sex) {
        this.id = id;
        this.realName = realName;
        this.email = email;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

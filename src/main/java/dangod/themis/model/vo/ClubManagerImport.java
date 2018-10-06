package dangod.themis.model.vo;

import dangod.themis.model.po.common.UserBaseInfo;

import java.util.List;

/**
 * Created by ruilin on 2018/9/18.
 */
public class ClubManagerImport {
    private String stuId;
    private String realName;
    private String clubName;
    private UserBaseInfo baseInfo;
    private String phone;
    private String email;
    private Double selfMoney;
    private Double reserveMoney;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public UserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSelfMoney() {
        return selfMoney;
    }

    public void setSelfMoney(Double selfMoney) {
        this.selfMoney = selfMoney;
    }

    public Double getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClubManagerImport(){

    }
    public ClubManagerImport(List<String> list){
        this.clubName=list.get(1);
        this.realName=list.get(2);
        this.stuId=list.get(3);
        this.phone=list.get(4);
        this.email=list.get(5);

        //默认给0
        this.selfMoney=0.0;
        this.reserveMoney=0.0;
    }
}

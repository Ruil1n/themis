package dangod.themis.model.vo.club;

import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.common.User;

import javax.persistence.*;

public class ClubVo {
    private long clubId;
    private String clubName;
    private String chiefName;
    private long userId;
    private Double selfMoney;
    private Double reserveMoney;

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public ClubVo() {
    }

    public ClubVo(Club club) {
        this.clubId = club.getId();
        this.clubName = club.getClubName();
        this.chiefName = club.getBaseInfo().getRealName();
        this.userId = club.getBaseInfo().getUser().getId();
        this.selfMoney = club.getSelfMoney();
        this.reserveMoney = club.getReserveMoney();
    }


    public ClubVo(Integer clubId, String clubName, String chiefName, Integer userId, Double selfMoney, Double reserveMoney) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.chiefName = chiefName;
        this.userId = userId;
        this.selfMoney = selfMoney;
        this.reserveMoney = reserveMoney;
    }
}

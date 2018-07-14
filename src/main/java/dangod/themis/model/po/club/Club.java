package dangod.themis.model.po.club;

import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "club_club")
public class Club {
    @Id
    @GeneratedValue
    private long id;
    private String clubName;
    @OneToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="chief_id",nullable=true)
    private UserBaseInfo baseInfo;
    private Double selfMoney;
    private Double reserveMoney;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Club() {
    }

    public Club(String clubName, UserBaseInfo baseInfo, Double selfMoney, Double reserveMoney) {
        this.clubName = clubName;
        this.baseInfo = baseInfo;
        this.selfMoney = selfMoney;
        this.reserveMoney = reserveMoney;
    }
}

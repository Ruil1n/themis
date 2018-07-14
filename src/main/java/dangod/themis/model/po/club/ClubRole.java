package dangod.themis.model.po.club;

import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "club_role")
public class ClubRole {
    @Id
    @GeneratedValue
    private long id;
    private Integer Lv;//0啥都不是 1社长 2社联财务 3社联主席 4社联指导老师
    @OneToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",nullable=false,unique = true)
    private UserBaseInfo baseInfo;

    public long getId() {
        return id;
    }


    public Integer getLv() {
        return Lv;
    }


    public UserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public ClubRole() {
    }

    public ClubRole(Integer lv, UserBaseInfo baseInfo) {
        Lv = lv;
        this.baseInfo = baseInfo;
    }
}

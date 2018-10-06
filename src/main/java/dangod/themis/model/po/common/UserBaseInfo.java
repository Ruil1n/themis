package dangod.themis.model.po.common;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "common_user_info")
public class UserBaseInfo implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String realName;
    private String email;
    private String sex;
    private String phone;
    @OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",nullable=true)
    private User user;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserBaseInfo() {
    }

    public UserBaseInfo(String realName, String email, String sex, String phone,User user) {
        this.realName = realName;
        this.email = email;
        this.sex = sex;
        this.user = user;
        this.phone=phone;
    }
}

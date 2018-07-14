package dangod.themis.model.po.authority;

import com.alibaba.fastjson.JSON;
import dangod.themis.model.po.common.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "core_authority_user")
public class AuthorityUser {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
    @Column(name = "authority_list")
    private String authorityList = "[]";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(String authorityList) {
        this.authorityList = authorityList;
    }

    public List<Long> getList() {
        return JSON.parseArray(authorityList, Long.class);
    }

    public void setList(List<Long> authorityList) {
        this.authorityList = JSON.toJSONString(authorityList);
    }
    public AuthorityUser() {
    }
    public AuthorityUser(User user, String authorityList) {
        this.user = user;
        this.authorityList = authorityList;
    }
}

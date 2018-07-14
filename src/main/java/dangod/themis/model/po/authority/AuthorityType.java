package dangod.themis.model.po.authority;

import javax.persistence.*;

@Entity
@Table(name = "core_authority_type")
public class AuthorityType {
    @Id
    private long id;
    private String name;

    @ManyToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="gourp_id",nullable=true)
    private AuthorityGourp gourp;//权限分组

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable=false)
    private AuthorityMenu menu;//权限对应菜单

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorityGourp getGourp() {
        return gourp;
    }

    public void setGourp(AuthorityGourp gourp) {
        this.gourp = gourp;
    }

    public AuthorityMenu getMenu() {
        return menu;
    }

    public void setMenu(AuthorityMenu menu) {
        this.menu = menu;
    }

    public AuthorityType(){

    }

    public AuthorityType(long id, String name, AuthorityGourp gourp, AuthorityMenu menu) {
        this.id = id;
        this.name = name;
        this.gourp = gourp;
        this.menu = menu;
    }
}

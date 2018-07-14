package dangod.themis.model.po.common;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "common_inform")
public class Inform {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 6000)
    private String content;
    private Timestamp date;
    private Timestamp modified;

    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="user_id",nullable=true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public void updateDate() {
        this.modified = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inform(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.date = new Timestamp(Calendar.getInstance().getTime().getTime());
        this.modified = this.date;
        this.user = user;
    }

    public Inform() {
        this.date = new Timestamp(Calendar.getInstance().getTime().getTime());
        this.modified = this.date;
    }
}

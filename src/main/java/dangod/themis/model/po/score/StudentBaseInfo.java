package dangod.themis.model.po.score;

import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_base_info")
public class StudentBaseInfo {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
    private String stuId;
    private String photo;
    private String entranceTime;
    private String political;

    @OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="base_id",nullable=false, unique = true)
    private UserBaseInfo baseInfo;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="class_id",nullable=true)
    private Class aClass;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="dormitory_id",nullable=true)
    private Dormitory dormitory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(String entrance_time) {
        this.entranceTime = entrance_time;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public UserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public StudentBaseInfo() {
    }

    public StudentBaseInfo(String stuId, String photo, String entranceTime, String political, UserBaseInfo baseInfo, Class aClass, Dormitory dormitory) {
        this.stuId = stuId;
        this.photo = photo;
        this.entranceTime = entranceTime;
        this.political = political;
        this.baseInfo = baseInfo;
        this.aClass = aClass;
        this.dormitory = dormitory;
    }
}

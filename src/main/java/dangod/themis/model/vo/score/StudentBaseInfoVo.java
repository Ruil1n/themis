package dangod.themis.model.vo.score;

import dangod.themis.model.po.score.StudentBaseInfo;

public class StudentBaseInfoVo {
    private long userId;
    private long classId;
    private long dormitoryId;
    private String stuId;
    private String realName;
    private String sex;
    private String className;
    private String photo;
    private String entrance_time;
    private String dormitory;
    private String political;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEntrance_time() {
        return entrance_time;
    }

    public void setEntrance_time(String entrance_time) {
        this.entrance_time = entrance_time;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public StudentBaseInfoVo() {
    }

    public StudentBaseInfoVo(StudentBaseInfo baseInfo) {
        this.userId = baseInfo.getBaseInfo().getUser().getId();
        this.stuId = baseInfo.getStuId();
        this.realName = baseInfo.getBaseInfo().getRealName();
        this.sex = baseInfo.getBaseInfo().getSex();
        if(baseInfo.getaClass()!=null) {
            this.className = baseInfo.getaClass().getMajor().getYear() + "级" + baseInfo.getaClass().getMajor().getMajorName() + baseInfo.getaClass().getClassNum() + "班";
            this.classId = baseInfo.getaClass().getId();
        }else {
            this.className = "未分班";
            this.classId = -1;
        }
        this.photo = baseInfo.getPhoto() == null ? "" : baseInfo.getPhoto();
        this.entrance_time = baseInfo.getEntranceTime();
        if(baseInfo.getDormitory()!=null) {
            this.dormitory = baseInfo.getDormitory().getBuild() + "号楼" + baseInfo.getDormitory().getFloor() + "-" + baseInfo.getDormitory().getRoom();
            this.dormitoryId = baseInfo.getDormitory().getId();
        }else {
            this.dormitory = "未分寝";
            this.dormitoryId = -1;
        }
        this.political = baseInfo.getPolitical();
    }

    public StudentBaseInfoVo(long userId, String stuId, String realName, String sex, String className, String photo, String entrance_time, String dormitory, String political) {
        this.userId = userId;
        this.stuId = stuId;
        this.realName = realName;
        this.sex = sex;
        this.className = className;
        this.photo = photo;
        this.entrance_time = entrance_time;
        this.dormitory = dormitory;
        this.political = political;
    }
}

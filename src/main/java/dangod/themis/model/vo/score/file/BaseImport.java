package dangod.themis.model.vo.score.file;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

public class BaseImport {
    private String stuId;
    private String realName;
    private String sex;
    private String email;
    private String political;
    private String entranceTime;
    private long classId;
    private long dormitoryId;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(String entranceTime) {
        this.entranceTime = entranceTime;
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

    public BaseImport() {
    }

    public BaseImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.sex = list.get(2);
        this.email = list.get(3);
        this.political = list.get(4);
        this.entranceTime = list.get(5);
        try {
            this.classId = Long.parseLong(list.get(6));
        }catch (Exception e){
            this.classId = -1L;
        }
        try {
            this.dormitoryId = Long.parseLong(list.get(7));
        }catch (Exception e){
            this.dormitoryId = -1L;
        }
    }
}

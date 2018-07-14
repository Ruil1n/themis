package dangod.themis.model.vo.score.file;

import java.util.List;

public class PracticeImport {
    private String stuId;
    private String realName;
    private String practiceName;
    private String practiceDate;
    private Integer result;
    private String common;

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

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(String practiceDate) {
        this.practiceDate = practiceDate;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public PracticeImport() {
    }

    public PracticeImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.practiceName = list.get(2);
        this.practiceDate = list.get(3);
        switch (list.get(4)){
            case "优秀": this.result = 1;break;
            case "合格": this.result = 0;break;
            case "不合格": this.result = -1;break;
            default: this.result = -1;
        }
        this.common = list.get(5);
    }
}

package dangod.themis.model.vo.score.file;

import java.util.List;

public class SkillImport {
    private String stuId;
    private String realName;
    private String skillName;
    private Integer skillLv;
    private Double skillScore;
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getSkillLv() {
        return skillLv;
    }

    public void setSkillLv(Integer skillLv) {
        this.skillLv = skillLv;
    }

    public Double getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Double skillScore) {
        this.skillScore = skillScore;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public SkillImport() {
    }

    public SkillImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.skillName = list.get(2);
        switch (list.get(3)){
            case "未定义": this.skillLv = -1;break;
            case "院级": this.skillLv = 1;break;
            case "校级": this.skillLv = 2;break;
            case "区级": this.skillLv = 3;break;
            case "市级": this.skillLv = 4;break;
            case "省级": this.skillLv = 5;break;
            case "国家级": this.skillLv = 6;break;
            case "国际级": this.skillLv = 7;break;
            default: this.skillLv = -1;
        }
        this.skillScore = Double.parseDouble(list.get(4));
        this.common = list.get(5);
    }
}

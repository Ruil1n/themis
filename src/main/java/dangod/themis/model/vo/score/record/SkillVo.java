package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Skill;

public class SkillVo extends BaseRecordVo{
    private String skillLv;
    private String skillName;
    private Double skillScore;

    public String getSkillLv() {
        return skillLv;
    }

    public void setSkillLv(String skillLv) {
        this.skillLv = skillLv;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Double getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Double skillScore) {
        this.skillScore = skillScore;
    }

    public SkillVo() {
    }

    public SkillVo(Skill skill) {
        this.recordId = skill.getId();
        this.stuId = skill.getBaseInfo().getStuId();
        this.common = skill.getCommon();
        this.term = skill.getTerm();
        this.skillScore = skill.getSkillScore();
        switch (skill.getSkillLv()){
            case -1: this.skillLv = "未定义";break;
            case 1: this.skillLv = "院级";break;
            case 2: this.skillLv = "校级";break;
            case 3: this.skillLv = "区级";break;
            case 4: this.skillLv = "市级";break;
            case 5: this.skillLv = "省级";break;
            case 6: this.skillLv = "国家级";break;
            case 7: this.skillLv = "国际级";break;
            default: this.skillLv = "未定义";
        }
    }
}

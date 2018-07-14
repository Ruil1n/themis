package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Office;

import static dangod.themis.config.ScoreConstant.OFFICE_EXTRA;
import static dangod.themis.config.ScoreConstant.OFFICE_LV;

public class OfficeVo extends BaseRecordVo {
    private String officeName;
    private String officeLv;
    private String startDate;
    private String endDate;
    private String result;
    private double score;

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeLv() {
        return officeLv;
    }

    public void setOfficeLv(String officeLv) {
        this.officeLv = officeLv;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public OfficeVo() {
    }

    public OfficeVo(Office office) {
        this.recordId = office.getId();
        this.stuId = office.getBaseInfo().getStuId();
        this.common = office.getCommon();
        this.term = office.getTerm();
        this.officeName = office.getOfficeName();
        this.startDate = office.getStartDate();
        this.endDate = office.getEndDate();
        this.officeLv = office.getOfficeLv()+"级岗";
        switch (office.getResult()){
            case -1:
                this.result = "不合格";
                this.score = 0;
                break;
            case 0:
                this.result = "合格";
                this.score = OFFICE_LV[office.getOfficeLv() - 1];
                break;
            case 1:
                this.result = "优秀";
                this.score = OFFICE_LV[office.getOfficeLv() - 1] * OFFICE_EXTRA;
                break;
            default:
                this.result = "未定义";
                this.score = 0;
        }
    }
}

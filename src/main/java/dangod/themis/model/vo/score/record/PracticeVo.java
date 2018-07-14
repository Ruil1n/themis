package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Practice;

import static dangod.themis.config.ScoreConstant.*;

public class PracticeVo extends BaseRecordVo {
    private String practiceName;
    private String practiceDate;
    private String result;
    private double score;

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

    public PracticeVo() {
    }

    public PracticeVo(Practice practice) {
        this.recordId = practice.getId();
        this.stuId = practice.getBaseInfo().getStuId();
        this.common = practice.getCommon();
        this.term = practice.getTerm();
        this.practiceDate = practice.getPracticeDate();
        this.practiceName = practice.getPracticeName();
        switch (practice.getResult()){
            case -1:
                this.result = "不合格";
                this.score = 0;
                break;
            case 0:
                this.result = "合格";
                this.score = PRACTICE_SCORE;
                break;
            case 1:
                this.result = "优秀";
                this.score = PRACTICE_SCORE * PRACTICE_EXTRA;
                break;
            default:
                this.result = "未定义";
                this.score = 0;
        }

    }
}

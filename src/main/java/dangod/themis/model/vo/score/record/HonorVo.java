package dangod.themis.model.vo.score.record;


import dangod.themis.model.po.score.record.Honor;

public class HonorVo extends BaseRecordVo {
    private String honorName;
    private String honorLv;
    private Double honorScore;

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorLv() {
        return honorLv;
    }

    public void setHonorLv(String honorLv) {
        this.honorLv = honorLv;
    }

    public Double getHonorScore() {
        return honorScore;
    }

    public void setHonorScore(Double honorScore) {
        this.honorScore = honorScore;
    }

    public HonorVo() {
    }

    public HonorVo(Honor honor) {
        this.recordId = honor.getId();
        this.stuId = honor.getBaseInfo().getStuId();
        this.common = honor.getCommon();
        this.term = honor.getTerm();
        this.honorName = honor.getHonorName();
        this.honorScore = honor.getHonorScore();
        switch (honor.getHonorLv()){
            case -1: this.honorLv = "未定义";break;
            case 1: this.honorLv = "院级";break;
            case 2: this.honorLv = "校级";break;
            case 3: this.honorLv = "区级";break;
            case 4: this.honorLv = "市级";break;
            case 5: this.honorLv = "省级";break;
            case 6: this.honorLv = "国家级";break;
            case 7: this.honorLv = "国际级";break;
            default: this.honorLv = "未定义";
        }
    }


}

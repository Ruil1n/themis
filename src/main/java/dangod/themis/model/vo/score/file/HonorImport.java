package dangod.themis.model.vo.score.file;

import java.util.List;

public class HonorImport {
    private String stuId;
    private String realName;
    private String honorName;
    private Integer honorLv;
    private Double honorScore;
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

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public Double getHonorScore() {
        return honorScore;
    }

    public void setHonorScore(Double honorScore) {
        this.honorScore = honorScore;
    }

    public Integer getHonorLv() {
        return honorLv;
    }

    public void setHonorLv(Integer honorLv) {
        this.honorLv = honorLv;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public HonorImport() {
    }

    public HonorImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.honorName = list.get(2);
        switch (list.get(3)){
            case "未定义": this.honorLv = -1;break;
            case "院级": this.honorLv = 1;break;
            case "校级": this.honorLv = 2;break;
            case "区级": this.honorLv = 3;break;
            case "市级": this.honorLv = 4;break;
            case "省级": this.honorLv = 5;break;
            case "国家级": this.honorLv = 6;break;
            case "国际级": this.honorLv = 7;break;
            default: this.honorLv = -1;
        }
        this.honorScore = Double.parseDouble(list.get(4));
        this.common = list.get(5);
    }
}

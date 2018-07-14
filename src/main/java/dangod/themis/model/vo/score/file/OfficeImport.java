package dangod.themis.model.vo.score.file;

import java.util.Calendar;
import java.util.List;

public class OfficeImport {
    private String stuId;
    private String realName;
    private Integer officeLv;
    private String officeName;
    private String startDate;
    private String endDate;
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

    public Integer getOfficeLv() {
        return officeLv;
    }

    public void setOfficeLv(Integer officeLv) {
        this.officeLv = officeLv;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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

    public OfficeImport() {
    }

    public OfficeImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.officeLv = Integer.parseInt(list.get(2));
        this.officeName = list.get(3);
        this.startDate = list.get(4);
        this.endDate = list.get(5);
        switch (list.get(6)){
            case "优秀": this.result = 1;break;
            case "合格": this.result = 0;break;
            case "不合格": this.result = -1;break;
            default: this.result = -1;
        }
        this.common = list.get(7);
    }
}

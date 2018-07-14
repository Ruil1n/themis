package dangod.themis.model.vo.score.file.result;

import java.util.List;

public class ImportResult {
    private Integer successNum;
    private Integer FailNum;
    private List<String> failMessage;

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailNum() {
        return FailNum;
    }

    public void setFailNum(Integer failNum) {
        FailNum = failNum;
    }

    public List<String> getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(List<String> failMessage) {
        this.failMessage = failMessage;
    }

    public ImportResult(Integer successNum, Integer failNum, List<String> failMessage) {
        this.successNum = successNum;
        FailNum = failNum;
        this.failMessage = failMessage;
    }
}

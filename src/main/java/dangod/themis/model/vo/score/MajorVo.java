package dangod.themis.model.vo.score;

import dangod.themis.model.po.score.Major;

public class MajorVo {
    private long id;
    private String majorName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public MajorVo() {
    }

    public MajorVo(Major major) {
        this.id = major.getId();
        this.majorName = major.getMajorName();
    }
}

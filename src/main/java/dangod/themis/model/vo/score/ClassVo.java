package dangod.themis.model.vo.score;

import dangod.themis.model.po.score.Class;

public class ClassVo {
    private long id;
    private Integer classNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public ClassVo() {
    }

    public ClassVo(Class aClass) {
        this.id = aClass.getId();
        this.classNum = aClass.getClassNum();
    }
}

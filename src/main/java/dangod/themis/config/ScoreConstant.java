package dangod.themis.config;

public class ScoreConstant {
    public static final double ACTIVITY_SCORE = 0.5;//每个活动分数

    public static final double OFFICE_EXTRA = 1.2;//任职优秀额外倍数
    public static final double[] OFFICE_LV = {
            7, //一级岗基础加分
            5, //二级岗基础加分
            4, //三级岗基础加分
            3, //四级岗基础加分
            2, //五级岗基础加分
            1, //六级岗基础加分
            0.5, //七级岗基础加分
    };

    public static final double PRACTICE_EXTRA = 1.2;//社会实践优秀额外倍数
    public static final double PRACTICE_SCORE = 0.5;//社会实践基础分数

}

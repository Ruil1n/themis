package dangod.themis.model.po.authority.constant;

import dangod.themis.model.po.authority.AuthorityType;

import java.util.HashMap;
import java.util.Map;

import static dangod.themis.model.po.authority.constant.MenuTable.MENU_TABLE;

public class TypeTable {
    public static final Map<Long, AuthorityType> TYPE_TABLE = new HashMap();
    //id是权限，后面是对应菜单
    static{
        TYPE_TABLE.put(1L, new AuthorityType(1L, "个人管理", null, MENU_TABLE.get(1L)));
        TYPE_TABLE.put(2L, new AuthorityType(2L, "发送公告", null, MENU_TABLE.get(3L)));
        TYPE_TABLE.put(3L, new AuthorityType(3L, "我的公告", null, MENU_TABLE.get(4L)));
        TYPE_TABLE.put(4L, new AuthorityType(4L, "我的公告", null, MENU_TABLE.get(5L)));
        TYPE_TABLE.put(5L, new AuthorityType(5L, "用户管理", null, MENU_TABLE.get(7L)));

        TYPE_TABLE.put(6L, new AuthorityType(6L, "学生信息查看", null, MENU_TABLE.get(10L)));
        TYPE_TABLE.put(7L, new AuthorityType(7L, "基础信息管理", null, MENU_TABLE.get(8L)));
        TYPE_TABLE.put(8L, new AuthorityType(8L, "班级信息管理", null, MENU_TABLE.get(11L)));
        TYPE_TABLE.put(9L, new AuthorityType(9L, "专业信息管理", null, MENU_TABLE.get(12L)));
        TYPE_TABLE.put(10L, new AuthorityType(10L, "全校信息管理", null, MENU_TABLE.get(13L)));

        TYPE_TABLE.put(11L, new AuthorityType(11L, "学生基础管理", null, MENU_TABLE.get(14L)));
        TYPE_TABLE.put(12L, new AuthorityType(12L, "学生活动加分", null, MENU_TABLE.get(15L)));
        TYPE_TABLE.put(13L, new AuthorityType(13L, "学生荣誉加分", null, MENU_TABLE.get(16L)));
        TYPE_TABLE.put(14L, new AuthorityType(14L, "学生任职加分", null, MENU_TABLE.get(17L)));
        TYPE_TABLE.put(15L, new AuthorityType(15L, "学生实践加分", null, MENU_TABLE.get(18L)));
        TYPE_TABLE.put(16L, new AuthorityType(16L, "学生志愿加分", null, MENU_TABLE.get(19L)));
        TYPE_TABLE.put(17L, new AuthorityType(17L, "学生技能加分", null, MENU_TABLE.get(20L)));
        TYPE_TABLE.put(18L, new AuthorityType(18L, "学生其他加分", null, MENU_TABLE.get(21L)));
        //club
        TYPE_TABLE.put(19L, new AuthorityType(19L, "活动申请", null, MENU_TABLE.get(23L)));
        TYPE_TABLE.put(20L, new AuthorityType(20L, "申请查看", null, MENU_TABLE.get(24L)));
        TYPE_TABLE.put(21L, new AuthorityType(21L, "申请审批", null, MENU_TABLE.get(25L)));
        //TYPE_TABLE.put(22L, new AuthorityType(22L, "申请退款", null, null));
    }
}

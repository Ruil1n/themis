package dangod.themis.model.po.authority.constant;

import dangod.themis.model.po.authority.AuthorityMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuTable {
    public static final Map<Long, AuthorityMenu> MENU_TABLE = new HashMap();
    static{
        MENU_TABLE.put(1L, new AuthorityMenu(1L, "db", "cogs", "德育分数据库", "/db", null));
        MENU_TABLE.put(14L, new AuthorityMenu(14L, "db/stu", "cogs", "学生基础管理", "stu", MENU_TABLE.get(1L)));
        MENU_TABLE.put(15L, new AuthorityMenu(15L, "db/activity", "cogs", "学生活动加分", "activity", MENU_TABLE.get(1L)));
        MENU_TABLE.put(16L, new AuthorityMenu(16L, "db/honor", "cogs", "学生荣誉加分", "honor", MENU_TABLE.get(1L)));
        MENU_TABLE.put(17L, new AuthorityMenu(17L, "db/office", "cogs", "学生任职加分", "office", MENU_TABLE.get(1L)));
        MENU_TABLE.put(18L, new AuthorityMenu(18L, "db/practice", "cogs", "学生实践加分", "practice", MENU_TABLE.get(1L)));
        MENU_TABLE.put(19L, new AuthorityMenu(19L, "db/volunteer", "cogs", "学生志愿加分", "volunteer", MENU_TABLE.get(1L)));
        MENU_TABLE.put(20L, new AuthorityMenu(20L, "db/skill", "cogs", "学生技能加分", "skill", MENU_TABLE.get(1L)));
        MENU_TABLE.put(21L, new AuthorityMenu(21L, "db/reserve", "cogs", "学生其他加分", "reserve", MENU_TABLE.get(1L)));


        MENU_TABLE.put(2L, new AuthorityMenu(2L, "inform", "file", "公告", "/inform", null));
        MENU_TABLE.put(3L, new AuthorityMenu(3L, "inform/send", "send", "公告发送", "send", MENU_TABLE.get(2L)));
        MENU_TABLE.put(4L, new AuthorityMenu(4L, "inform/mine", "envelope-open-o", "我的公告", "mine", MENU_TABLE.get(2L)));
        MENU_TABLE.put(5L, new AuthorityMenu(5L, "inform/manage", "file-text-o", "公告管理", "menage", MENU_TABLE.get(2L)));


        MENU_TABLE.put(6L, new AuthorityMenu(6L, "user", "cogs", "用户管理", "/user", null));
        MENU_TABLE.put(7L, new AuthorityMenu(7L, "user/account", "cogs", "账号管理", "account", MENU_TABLE.get(6L)));
        MENU_TABLE.put(8L, new AuthorityMenu(8L, "user/common", "cogs", "基础信息管理", "common", MENU_TABLE.get(6L)));

        MENU_TABLE.put(9L, new AuthorityMenu(9L, "student", "cogs", "德育分管理", "/common", null));
        MENU_TABLE.put(10L, new AuthorityMenu(10L, "student/self", "cogs", "个人信息查看", "self", MENU_TABLE.get(9L)));
        MENU_TABLE.put(11L, new AuthorityMenu(11L, "student/class", "cogs", "班级信息管理", "class", MENU_TABLE.get(9L)));
        MENU_TABLE.put(12L, new AuthorityMenu(12L, "student/major", "cogs", "专业信息管理", "major", MENU_TABLE.get(9L)));
        MENU_TABLE.put(13L, new AuthorityMenu(13L, "student/school", "cogs", "全校信息管理", "school", MENU_TABLE.get(9L)));

        MENU_TABLE.put(22L, new AuthorityMenu(22L, "club", "cogs", "社团管理", "/club", null));
        MENU_TABLE.put(23L, new AuthorityMenu(23L, "club/apply", "cogs", "活动申请", "apply", MENU_TABLE.get(22L)));
        MENU_TABLE.put(24L, new AuthorityMenu(24L, "club/check", "cogs", "申请查看", "check", MENU_TABLE.get(22L)));
        MENU_TABLE.put(25L, new AuthorityMenu(25L, "club/approve", "cogs", "申请审核", "approve", MENU_TABLE.get(22L)));



    }
    //total 25

}

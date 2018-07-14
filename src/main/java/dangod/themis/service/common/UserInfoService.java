package dangod.themis.service.common;

import dangod.themis.model.vo.UserBaseInfoVo;

import java.util.List;

public interface UserInfoService {

    //基础信息处理方法

    /**
     * 获取基础信息方法
     * @param userId
     * @return
     */
    UserBaseInfoVo getBaseInfoByUserId(long userId);

    /**
     * 修改基础信息
     * @param userId
     * @param realName
     * @param email
     * @param sex
     * @return
     */
    UserBaseInfoVo updateUserBaseInfo(long userId, String realName, String email, String sex);

    List<UserBaseInfoVo> getAllUserBaseInfo(Integer page, Integer size);
}

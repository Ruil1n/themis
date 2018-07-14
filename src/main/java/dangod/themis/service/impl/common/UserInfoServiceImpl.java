package dangod.themis.service.impl.common;

import dangod.themis.dao.common.UserBaseInfoRepo;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.model.vo.UserBaseInfoVo;
import dangod.themis.service.common.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserBaseInfoRepo baseInfoRepo;

    @Override
    public UserBaseInfoVo getBaseInfoByUserId(long userId) {
        UserBaseInfo userBaseInfo = baseInfoRepo.findByUser_Id(userId);
        if(userBaseInfo == null) return null;
        return new UserBaseInfoVo(userBaseInfo);
    }

    @Override
    public UserBaseInfoVo updateUserBaseInfo(long userId, String realName, String email, String sex) {
        UserBaseInfo baseInfo = getBaseInfoPoByUserId(userId);
        if(realName != null)
            baseInfo.setRealName(realName);
        if(sex != null)
            baseInfo.setSex(sex);
        baseInfo.setEmail(email);
        baseInfoRepo.save(baseInfo);
        if(baseInfo == null) return null;
        return new UserBaseInfoVo(baseInfo);
    }

    @Override
    public List<UserBaseInfoVo> getAllUserBaseInfo(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, new Sort("id"));
        Page<UserBaseInfo> poList= baseInfoRepo.findAll(pageable);
        List<UserBaseInfoVo> voList = new ArrayList<>();
        for(UserBaseInfo baseInfo : poList){
            voList.add(new UserBaseInfoVo(baseInfo));
        }
        return voList;
    }

    private UserBaseInfo getBaseInfoPoByUserId(long userId){
        return baseInfoRepo.findByUser_Id(userId);
    }
}

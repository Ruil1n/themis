package dangod.themis.service.impl.common;

import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.dao.common.UserBaseInfoRepo;
import dangod.themis.dao.common.UserRepo;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.service.common.UserService;
import dangod.themis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserBaseInfoRepo baseInfoRepo;
    @Autowired
    private AuthorityUserRepo authorityUserRepo;

    @Override
    public Long checkUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) return -1L;
        System.out.println(password);
        System.out.println(user.getSalt());
        System.out.println(MD5Util.MD5(password + user.getSalt()));
        if (!user.getPassword().equals(MD5Util.MD5(password + user.getSalt())))
            return -1L;
        return user.getId();
    }

    @Override
    public Integer addUser(String username, String password, String realName, String email, String sex) {
        if (userRepo.countByUsername(username) != 0)
            return -1;
        User user = new User(username, password);
        return addUserBaseInfo(realName, email, sex, user);
    }

    private Integer addUserBaseInfo(String realName, String email, String sex, User user) {
        UserBaseInfo baseInfo = baseInfoRepo.saveAndFlush((new UserBaseInfo(realName, email, sex, null,user)));
        //添加全部权限
        //权限见model.po.authority.constant
        //添加学生权限
        AuthorityUser authorityUser=new AuthorityUser(user,"[6]");
        authorityUserRepo.saveAndFlush(authorityUser);

        if(baseInfo == null)return -1;
        return 0;
    }

    @Override
    public Integer updatePassword(Long userId, String password) {
        User user = userRepo.findOne(userId);
        user.setSalt();
        user.updatePassword(password);
        User r = userRepo.saveAndFlush(user);
        if(r == null)return -1;
        return 0;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepo.findOne(userId);
    }

    @Override
    public UserBaseInfo addAndCheckUser(String username, String password, String realName, String email, String sex,String phone) {
        if (userRepo.countByUsername(username) != 0)
            return null;
        User user = new User(username, password);
        userRepo.saveAndFlush(user);
        UserBaseInfo baseInfo = addAndCheckUserBaseInfo(realName, email, sex, phone,user);
        if(baseInfo != null)return baseInfo;
        return null;
    }

    private UserBaseInfo addAndCheckUserBaseInfo(String realName, String email, String sex,String phone, User user) {
        UserBaseInfo baseInfo = baseInfoRepo.saveAndFlush(new UserBaseInfo(realName, email, sex, phone,user));
        if(baseInfo == null)return null;
        return baseInfo;
    }



}

package dangod.themis;

import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.dao.common.UserRepo;
import dangod.themis.model.po.authority.AuthorityType;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.po.common.User;
import dangod.themis.service.common.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThemisApplication.class)
@WebAppConfiguration
public class ThemisApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityUserRepo authorityUserRepo;

	@Autowired
	private UserRepo userRepo;

	@Test
	public void contextLoads() {
		userService.addUser("ruilin","ruilin","17905535","ruilin@qq.com","1");  //注册测试
		//权限见model.po.authority.constant
		//添加用户名与注册相同
//		User user=userService.getUserById((long) 3);
//		AuthorityUser authorityUser=new AuthorityUser(user,"[22,23,24,25]");
//		authorityUserRepo.saveAndFlush(authorityUser);

		//userService.checkUser("ruilin","ruilin"); //登陆测试
	}




}

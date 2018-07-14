package dangod.themis.service.core;

import dangod.themis.model.vo.MenuVo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface AuthorityService {
    /**
     * 初始化权限菜单表
     * @return
     */
    Integer initAuthorityTable();

    /**
     * 通过userId 获取用户的菜单
     * @param userId
     * @return
     */
    List<MenuVo> getMenuByUserId(long userId);

    /**
     * 通过userId 获取用户的权限列表
     * @param userId
     * @return
     */
    List<Long> getAuthoritiesByUserId(long userId);


}

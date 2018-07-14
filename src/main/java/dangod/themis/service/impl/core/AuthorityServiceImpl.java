package dangod.themis.service.impl.core;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.authority.AuthorityMenuRepo;
import dangod.themis.dao.authority.AuthorityTypeRepo;
import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.model.po.authority.AuthorityMenu;
import dangod.themis.model.po.authority.AuthorityType;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.vo.MenuVo;
import dangod.themis.service.core.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

import static dangod.themis.model.po.authority.constant.MenuTable.MENU_TABLE;
import static dangod.themis.model.po.authority.constant.TypeTable.TYPE_TABLE;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityUserRepo userRepo;
    @Autowired
    private AuthorityTypeRepo typeRepo;
    @Autowired
    private AuthorityMenuRepo menuRepo;

    @Override
    public Integer initAuthorityTable(){
        try {
            menuRepo.save(MENU_TABLE.values());
            menuRepo.flush();
            typeRepo.save(TYPE_TABLE.values());
            typeRepo.flush();
        }catch (Exception e){
            throw new RuntimeException("权限表初始化错误");
        }
        return 0;
    }


    @Cacheable(value = "30m", key = "'user_menu_'+#userId")
    @Override
    public List<MenuVo> getMenuByUserId(long userId) {

        //获取用户-权限
        List<Long> list = getAuthoritiesByUserId(userId);

        //获取权限列表
        List<AuthorityType> authorityList = typeRepo.findAllByIdIn(list);

        //转化菜单
        Map<Long, AuthorityMenu> menuMap = getMenuListByAuthorityList(authorityList);

        //构建菜单结构
        Map<Long, MenuVo> t = new HashMap<>();
        for(AuthorityMenu menu : menuMap.values()){
            t.put(menu.getId(), new MenuVo(menu));
        }


        Map<Long, MenuVo> menuVoMap = new HashMap<>();
        for(AuthorityMenu menu : menuMap.values()){
            if(menu.getParent() == null ){
                menuVoMap.put(menu.getId(), t.get(menu.getId()));
            }else{
                t.get(menu.getParent().getId()).setChildrenByMenuVo(t.get(menu.getId()));
            }
        }
        List<MenuVo> menuVoList = new ArrayList<>();
        menuVoList.addAll(menuVoMap.values());

        return menuVoList;
    }

    @Cacheable(value = "30m", key = "'user_authory_'+#userId")
    @Override
    public List<Long> getAuthoritiesByUserId(long userId) {
        AuthorityUser user  = userRepo.findByUser_Id(userId);
        List<Long> list = user.getList();
        return list;
    }


    private Map<Long, AuthorityMenu> getMenuListByAuthorityList(List<AuthorityType> authorityList) {
        List<AuthorityMenu> authorityMenus = new ArrayList<>();
        for(AuthorityType type : authorityList)authorityMenus.add(type.getMenu());

        // 将构建菜单的所有母菜单子菜单取出放入map
        Map<Long, AuthorityMenu> menuMap = new HashMap<>();
        boolean allExist;
        while(authorityMenus != null){
            allExist = true;
            for(AuthorityMenu menu : authorityMenus){
                if(!menuMap.containsKey(menu.getId())) {
                    menuMap.put(menu.getId(), menu);
                    allExist = false;
                }
            }
            if(allExist)
                break;
            authorityMenus = findParentMenuList(authorityMenus);
        }
        return menuMap;
    }

    private List<AuthorityMenu> findParentMenuList(List<AuthorityMenu> authorityMenus){
        List<Long> menuList = new ArrayList<>();
        for(AuthorityMenu menu : authorityMenus){
            if(menu.getParent() != null)
                menuList.add(menu.getParent().getId());
        }
        return menuRepo.findAllByIdIn(menuList);
    }
}

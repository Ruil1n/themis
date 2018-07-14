package dangod.themis.service.impl.common;

import dangod.themis.dao.common.InformRepo;
import dangod.themis.model.po.common.Inform;
import dangod.themis.model.po.common.User;
import dangod.themis.model.vo.InformVo;
import dangod.themis.service.common.InformService;
import dangod.themis.service.common.UserInfoService;
import dangod.themis.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformServiceImpl implements InformService {

    @Autowired
    private InformRepo informRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;


    @Cacheable(value = "30m", key = "'inform_'+#id")
    @Override
    public InformVo getInformById(long id) {
        Inform inform = informRepo.findOne(id);
        if(inform == null) return null;
        String author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealName();
        return new InformVo(inform, author);
    }

    @Override
    public InformVo addInform(String title, String content, long userId) {
        User user = userService.getUserById(userId);
        Inform inform= informRepo.save(new Inform(title, content, user));
        if(inform == null) return null;
        String author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealName();
        return new InformVo(inform, author);
    }

    @CacheEvict(value = "30m", key = "'inform_'+#id")
    @Override
    public Integer deleteInformById(long id) {
        try {
            informRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Boolean updateInformValid(long informId, long userId) {
        try {
            if (getInformById(informId).getUserId() != userId) return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @CachePut(value = "30m", key = "'inform_'+#informId")
    @Override
    public InformVo updateInform(long informId, String title, String content, long userId) {
        Inform inform = getInformPoById(informId);
        inform.setTitle(title);
        inform.setContent(content);
        inform.setUser(userService.getUserById(userId));
        inform.updateDate();
        inform = informRepo.save(inform);
        if(inform == null) return null;
        String author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealName();
        return new InformVo(inform, author);
    }

    private Inform getInformPoById(long informId){
        return informRepo.findOne(informId);
    }

    @Override
    public List<InformVo> getListByUserId(long userId, Integer page, Integer size) {
        List<Inform> informList =  informRepo.findByUser_Id(userId, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "date")));
        if(informList == null) return null;
        List<InformVo> list = new ArrayList<>();
        String author = null;
        for(Inform inform : informList){
            author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealName();
            list.add(new InformVo(inform, author));
        }
        return list;
    }

    @Override
    public List<InformVo> getPageInform(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "date"));
        Page<Inform> informList =  informRepo.findAll(pageable);
        if(informList == null) return null;
        List<InformVo> list = new ArrayList<>();
        String author = null;
        for(Inform inform : informList){
            author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealName();
            list.add(new InformVo(inform, author));
        }
        return list;
    }


}

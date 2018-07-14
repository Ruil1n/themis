package dangod.themis.service.club;

import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.ClubFileVo;
import dangod.themis.model.vo.club.StatusVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ApplicationService {

    Integer apply(Club club, String name, String place, String start, String end,
                        String people, Double selfMoney, Double reserveMoney, Integer isFine, String introduce, MultipartFile file);
    ClubFileVo getFile(long applicationId);
    Integer applyRefund(Club club,long applicationId,Double realSelfMoney, Double realReserveMone);
    /**
     * 查看申请表(按照需求，未做身份验证，直接可以访问)
     * @param applicationId
     * @return
     */
    ApplicationVo getApplicationVo(long applicationId);

    /**
     * 获取自己社团申请情况
     * @param clubId
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getPageStatus(long clubId, Integer page, Integer size);

    /**
     * 按照status获取自己社团申请情况
     * @param clubId
     * @param status -1:未通过 0:通过 1:审核中 2:核账中
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getPageStatus(long clubId, Integer status, Integer page, Integer size);

    /**
     * 获取审批等级高于等于自己的申请(自己需要审批和自己审批过的)
     * @param clubRole
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer page, Integer size);//获取该等级下可以看到的所有可见申请

    /**
     * 获取审批等级高于等于自己的申请(自己需要审批和自己审批过的)
     * @param clubRole
     * @param status 指定搜索状态 -1:未通过 0:通过 1:审核中
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer status, Integer page, Integer size);//获取该等级下可以看到的所有可见申请

    /**
     * 获得需要当前用户审批的表单
     * @param clubRole
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getNeedApprovePage(ClubRole clubRole, Integer page, Integer size);//获取该等级下可以看到的所有可见申请

    /**
     *
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllPageStatus(Integer page, Integer size);//获取该等级下可以看到的所有可见申请

    /**
     *
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllPageStatus(Integer status, Integer page, Integer size);//获取该等级下可以看到的所有可见申请


    Integer deleteApplication(Club club, long applicationId);

    Integer deleteApplication(long applicationId);

}

package dangod.themis.service.common;

import dangod.themis.model.vo.InformVo;

import java.util.List;

public interface InformService {
    /**
     * 获取通知
     * @param id
     * @return
     */
    InformVo getInformById(long id);

    /**
     * 添加通知
     * @param title
     * @param content
     * @param userId
     * @return
     */
    InformVo addInform(String title, String content, long userId);

    /**
     * 删除通知
     * @param id
     * @return 0:success
     */
    Integer deleteInformById(long id);

    /**
     * 验证 inform 修改合法性
     * @param informId
     * @param userId
     * @return
     */
    Boolean updateInformValid(long informId, long userId);

    /**
     * 修改通知
     * @param informId
     * @param title
     * @param content
     * @param userId
     * @return
     */
    InformVo updateInform(long informId, String title, String content, long userId);

    /**
     * 获取用户的通知
     * @param userId
     * @return
     */
    List<InformVo> getListByUserId(long userId, Integer page, Integer size);

    /**
     * 获取分页通知
     * @param page
     * @param size
     * @return
     */
    List<InformVo> getPageInform(Integer page, Integer size);

    /**
     * @param page
     * @return
     */
}

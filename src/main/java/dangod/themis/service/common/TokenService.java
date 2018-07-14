package dangod.themis.service.common;


public interface TokenService {
    /**
     * 生成用户的token
     * @param userId
     * @return
     */
    String createToken(long userId);

    /**
     * 检查token是否合法
     * @param token
     * @return
     */
    boolean checkToken(String token);

    /**
     * 删除token
     * @param userId
     */
    void deleteToken(long userId);

    /**
     * 删除token
     * @param token
     */
    void deleteToken(String token);
}

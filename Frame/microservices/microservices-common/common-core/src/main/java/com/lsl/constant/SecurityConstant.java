package com.lsl.constant;

/**
 * 常量
 */
public interface SecurityConstant {

    /*
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /*
     * 用户名
     */
    String USERNAME = "username";

    /*
     * 用户信息
     */
    String DETAILS_USER = "user_info";

    /*
     * 用户ID
     */
    String DETAILS_USER_ID = "user_id";

    /*
     * 内部
     */
    String FROM_IN = "Y";

    /*
     * 标志
     */
    String FROM = "from";

    /*
     * 客户端ID
     */
    String CLIENT_ID = "clientId";

    /*
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /*
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth2/token";

    /*
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";

    /*
     * 协议字段
     */
    String DETAILS_LICENSE = "license";

    /*
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";

    /*
     * {noop} 加密的特征码
     */
    String NOOP = "{noop}";

    /*
     * 授权码模式confirm
     */
    String CUSTOM_CONSENT_PAGE_URI = "/token/confirm_access";
}

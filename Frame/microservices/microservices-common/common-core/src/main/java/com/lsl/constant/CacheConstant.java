package com.lsl.constant;

/**
 * 缓存的key 常量
 */
public interface CacheConstant {

    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "client:details";

    /**
     * oauth 缓存前缀
     */
    String PROJECT_OAUTH_ACCESS = "token::access_token";
}

/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package feign;

import com.lsl.constant.SecurityConstant;
import com.lsl.constant.ServiceNameConstant;
import com.lsl.utils.R;
import dto.UserDTO;
import dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 远程调用人员服务Feign通信客户端
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstant.PERSON_SERVICE)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param user 用户查询对象
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/user/info/query")
    R<UserInfo> info(@SpringQueryMap UserDTO user, @RequestHeader(SecurityConstant.FROM) String from);

    /**
     * 锁定用户
     *
     * @param username 用户名
     * @param from     调用标识
     * @return R
     */
    @PutMapping("/user/lock/{username}")
    R<Boolean> lockUser(@PathVariable("username") String username, @RequestHeader(SecurityConstant.FROM) String from);

}

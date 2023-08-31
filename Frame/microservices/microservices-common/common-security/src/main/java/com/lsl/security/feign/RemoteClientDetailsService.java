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

package com.lsl.security.feign;


import com.lsl.constant.SecurityConstant;
import com.lsl.constant.ServiceNameConstant;
import com.lsl.security.entity.OauthClientDetails;
import com.lsl.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 向AUTH服务发起请求
 */
@FeignClient(contextId = "remoteClientDetailsService", value = ServiceNameConstant.AUTH_SERVICE)
public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     *
     * @param clientId 用户名
     * @param from     调用标志
     * @return R
     */
    @GetMapping("/client/getClientDetailsById/{clientId}")
    R<OauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId,
                                               @RequestHeader(SecurityConstant.FROM) String from);

    /**
     * 查询全部客户端
     *
     * @param from 调用标识
     * @return R
     */
    @GetMapping("/client/list")
    R<List<OauthClientDetails>> listClientDetails(@RequestHeader(SecurityConstant.FROM) String from);

}

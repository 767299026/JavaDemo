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

package com.lsl.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.security.entity.OauthClientDetails;
import com.lsl.utils.R;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface OauthClientDetailsService extends IService<OauthClientDetails> {

    /**
     * 根据客户端信息
     *
     * @param clientDetails
     * @return
     */
    Boolean updateClientById(OauthClientDetails clientDetails);

    /**
     * 添加客户端
     *
     * @param clientDetails
     * @return
     */
    Boolean saveClient(OauthClientDetails clientDetails);

    /**
     * 分页查询客户端信息
     *
     * @param page
     * @param query
     * @return
     */
    Page queryPage(Page page, OauthClientDetails query);

    /**
     * 同步缓存 （清空缓存）
     *
     * @return R
     */
    R syncClientCache();

}

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

package com.lsl.auth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.auth.service.OauthClientDetailsService;
import com.lsl.security.annotation.Inner;
import com.lsl.security.entity.OauthClientDetails;
import com.lsl.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final OauthClientDetailsService clientDetailsService;

    /**
     * 通过ID查询
     *
     * @param clientId clientId
     * @return SysOauthClientDetails
     */
    @GetMapping("/{clientId}")
    public R getByClientId(@PathVariable String clientId) {
        OauthClientDetails details = clientDetailsService
                .getOne(Wrappers.<OauthClientDetails>lambdaQuery().eq(OauthClientDetails::getClientId, clientId));
        return R.ok(details);
    }

    /**
     * 简单分页查询
     */
    @GetMapping("/page")
    public R getOauthClientDetailsPage(Page page, OauthClientDetails oauthClientDetails) {
        LambdaQueryWrapper<OauthClientDetails> wrapper = Wrappers.<OauthClientDetails>lambdaQuery()
                .like(StrUtil.isNotBlank(oauthClientDetails.getClientId()), OauthClientDetails::getClientId,
                        oauthClientDetails.getClientId())
                .like(StrUtil.isNotBlank(oauthClientDetails.getClientSecret()), OauthClientDetails::getClientSecret,
                        oauthClientDetails.getClientSecret());
        return R.ok(clientDetailsService.page(page, wrapper));
    }

    /**
     * 添加
     *
     * @param clientDetails 实体
     * @return success/false
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_client_add')")
    public R add(@Valid @RequestBody OauthClientDetails clientDetails) {
        return R.ok(clientDetailsService.saveClient(clientDetails));
    }

    /**
     * 删除
     *
     * @param ids ID 列表
     * @return success/false
     */
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys_client_del')")
    public R removeById(@RequestBody Long[] ids) {
        clientDetailsService.removeByIds(CollUtil.toList(ids));
        return R.ok();
    }

    /**
     * 编辑
     *
     * @param clientDetails 实体
     * @return success/false
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_client_edit')")
    public R update(@Valid @RequestBody OauthClientDetails clientDetails) {
        return R.ok(clientDetailsService.updateClientById(clientDetails));
    }

    @Inner(false)
    @GetMapping("/getClientDetailsById/{clientId}")
    public R getClientDetailsById(@PathVariable String clientId) {
        return R.ok(clientDetailsService.getOne(
                Wrappers.<OauthClientDetails>lambdaQuery().eq(OauthClientDetails::getClientId, clientId), false));
    }

    /**
     * 查询全部客户端
     *
     * @return
     */
    @Inner(false)
    @GetMapping("/list")
    public R listClients() {
        return R.ok(clientDetailsService.list());
    }

    /**
     * 同步缓存字典
     *
     * @return R
     */
    @PutMapping("/sync")
    public R sync() {
        return clientDetailsService.syncClientCache();
    }

}

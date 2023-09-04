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

package com.lsl.user.api.vo;

import com.lsl.user.api.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 前端展示对象
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 随机盐
     */
    private String salt;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * QQ openid
     */
    private String qqOpenid;

    /**
     * gitee openid
     */
    private String giteeOpenId;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 锁定标记
     */
    private String lockFlag;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色列表
     */
    private List<Role> roleList;


    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

}

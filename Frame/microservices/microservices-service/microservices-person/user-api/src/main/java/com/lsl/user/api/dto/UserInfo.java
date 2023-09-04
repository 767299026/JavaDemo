package com.lsl.user.api.dto;

import com.lsl.user.api.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;
}

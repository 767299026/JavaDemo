package com.lsl.user.service;

import com.lsl.user.api.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRolesByUserId(Long userId);

    Role queryRoleByCode(String roleCode);
}

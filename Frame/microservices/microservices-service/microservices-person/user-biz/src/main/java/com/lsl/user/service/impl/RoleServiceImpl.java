package com.lsl.user.service.impl;

import com.lsl.user.api.entity.Role;
import com.lsl.user.dao.RoleMapper;
import com.lsl.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleMapper.listRolesByUserId(userId);
    }

    @Override
    public Role queryRoleByCode(String roleCode) {
        return roleMapper.queryRoleByCode(roleCode);
    }
}

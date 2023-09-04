package com.lsl.user.dao;

import com.lsl.user.api.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> listRolesByUserId(Long userId);

    Role queryRoleByCode(String roleCode);
}

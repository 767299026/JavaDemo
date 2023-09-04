package com.lsl.user.dao;

import com.lsl.user.api.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {

    void deleteAllByUserIds(List<Long> userIds);

    void insert(UserRole userRole);

    void batchInsert(List<UserRole> userRoles);

    void deleteByUserIds(Long userId);
}

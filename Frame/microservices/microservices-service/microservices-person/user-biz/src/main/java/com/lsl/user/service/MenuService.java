package com.lsl.user.service;

import com.lsl.user.api.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findMenuByRoleId(Long roleId);
}

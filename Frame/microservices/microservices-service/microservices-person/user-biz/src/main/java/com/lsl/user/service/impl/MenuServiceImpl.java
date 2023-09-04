package com.lsl.user.service.impl;

import com.lsl.user.api.entity.Menu;
import com.lsl.user.dao.MenuMapper;
import com.lsl.user.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuByRoleId(Long roleId) {
        return menuMapper.listMenusByRoleId(roleId);
    }
}

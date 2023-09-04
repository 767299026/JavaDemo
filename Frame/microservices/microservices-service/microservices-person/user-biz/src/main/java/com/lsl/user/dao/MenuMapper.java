package com.lsl.user.dao;

import com.lsl.user.api.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    List<Menu> listMenusByRoleId(Long roleId);
}

package com.lsl.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.constant.CacheConstant;
import com.lsl.constant.Constant;
import com.lsl.constant.ErrorCodes;
import com.lsl.security.utils.SpringSecurityUtils;
import com.lsl.user.api.dto.UserDTO;
import com.lsl.user.api.dto.UserInfo;
import com.lsl.user.api.entity.Menu;
import com.lsl.user.api.entity.Role;
import com.lsl.user.api.entity.User;
import com.lsl.user.api.entity.UserRole;
import com.lsl.user.api.vo.UserVO;
import com.lsl.user.dao.UserMapper;
import com.lsl.user.dao.UserRoleMapper;
import com.lsl.user.service.MenuService;
import com.lsl.user.service.RoleService;
import com.lsl.user.service.UserService;
import com.lsl.utils.MsgUtil;
import com.lsl.utils.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleService roleService;

    private final MenuService menuService;

    private final CacheManager cacheManager;

    @Override
    public Boolean saveUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setDelFlag(Constant.STATUS_NORMAL);
        user.setCreateBy(userDto.getUsername());
        user.setPassword(ENCODER.encode(userDto.getPassword()));
        userMapper.insert(user);
        // 如果角色为空，赋默认角色
        if (CollUtil.isEmpty(userDto.getRole())) {
            // 默认角色
            String defaultRole = "GENERAL_USER";
            Role role = roleService.queryRoleByCode(defaultRole);
            userDto.setRole(Collections.singletonList(role.getRoleId()));
        }

        // 插入用户角色关系表
        List<UserRole> userRoles = userDto.getRole().stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        userRoleMapper.batchInsert(userRoles);
        return Boolean.TRUE;
    }

    @Override
    public void removeBatchByIds(Long[] ids) {
        userMapper.removeAllByUserIds(CollUtil.toList(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteUserByIds(Long[] ids) {
        // 删除 spring cache
        List<User> userList = userMapper.queryAllByUserIds(CollUtil.toList(ids));
        Cache cache = cacheManager.getCache(CacheConstant.USER_DETAILS);
        for (User user : userList) {
            // 立即删除
            cache.evictIfPresent(user.getUsername());
        }
        userRoleMapper.deleteAllByUserIds(CollUtil.toList(ids));
        this.removeBatchByIds(ids);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUpdateTime(LocalDateTime.now());
        if (StrUtil.isNotBlank(userDto.getPassword())) {
            user.setPassword(ENCODER.encode(userDto.getPassword()));
        }
        userMapper.updateById(user);

        userRoleMapper.deleteByUserIds(userDto.getUserId());
        List<UserRole> userRoles = userDto.getRole().stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        userRoleMapper.batchInsert(userRoles);
        return Boolean.TRUE;
    }

    @Override
    public R<Boolean> updateUserInfo(UserDTO userDto) {
        UserVO userVO = userMapper.getUserVoByUsername(userDto.getUsername());
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setUserId(userVO.getUserId());
        user.setAvatar(userDto.getAvatar());
        user.setNickname(userDto.getNickname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userMapper.updateById(user);
        return R.ok();
    }

    @Override
    @CacheEvict(value = CacheConstant.USER_DETAILS, key = "#username")
    public R<Boolean> lockUser(String username) {
        User user = userMapper.queryInfoByName(username);
        if (Objects.nonNull(user)) {
            user.setLockFlag(Constant.STATUS_LOCK);
            userMapper.updateById(user);
        }
        return R.ok();
    }

    @Override
    public R changePassword(UserDTO userDto) {
        UserVO userVO = userMapper.getUserVoByUsername(userDto.getUsername());
        if (Objects.isNull(userVO)) {
            return R.failed("用户不存在");
        }

        if (StrUtil.isEmpty(userDto.getPassword())) {
            return R.failed("原密码不能为空");
        }

        if (!ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
            log.info("原密码错误，修改个人信息失败:{}", userDto.getUsername());
            return R.failed(MsgUtil.getMessage(ErrorCodes.USER_UPDATE_PASSWORDERROR));
        }

        if (StrUtil.isEmpty(userDto.getNewpassword1())) {
            return R.failed("新密码不能为空");
        }
        String password = ENCODER.encode(userDto.getNewpassword1());
        userMapper.updatePassword(password, userVO.getUserId());
        return R.ok();
    }

    @Override
    public R checkPassword(String password) {
        String username = SpringSecurityUtils.getUser().getUsername();
        User user = userMapper.queryInfoByName(username);
        if (!ENCODER.matches(password, user.getPassword())) {
            log.info("原密码错误");
            return R.failed("密码输入错误");
        } else {
            return R.ok();
        }
    }

    @Override
    public User queryInfo(String username, String phone) {
        return userMapper.queryInfoByNameAndPhone(username, phone);
    }

    @Override
    public UserVO selectUserVoById(Long id) {
        return userMapper.queryInfoById(id);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryInfoByName(username);
    }

    @Override
    public User query(User query) {
        return userMapper.query(query);
    }

    @Override
    public UserInfo findUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);

        // 设置角色列表 （ID）
        List<Long> roleIds = roleService.findRolesByUserId(user.getUserId())
                .stream()
                .map(Role::getRoleId)
                .collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));

        // 设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = menuService.findMenuByRoleId(roleId)
                    .stream()
                    .filter(menu -> StrUtil.isNotEmpty(menu.getPermission()))
                    .map(Menu::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

    @Override
    public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
        return userMapper.getUserVoPage(page, userDTO);
    }
}

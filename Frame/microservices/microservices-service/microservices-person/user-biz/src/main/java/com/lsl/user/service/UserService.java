package com.lsl.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.user.api.dto.UserDTO;
import com.lsl.user.api.dto.UserInfo;
import com.lsl.user.api.entity.User;
import com.lsl.user.api.vo.UserVO;
import com.lsl.utils.R;

public interface UserService {

    UserInfo findUserInfo(User user);

    User queryInfo(String username, String phone);

    User queryUserByUsername(String username);

    UserVO selectUserVoById(Long id);

    User query(User query);

    Boolean deleteUserByIds(Long[] ids);

    void removeBatchByIds(Long[] ids);

    Boolean saveUser(UserDTO userDto);

    Boolean updateUser(UserDTO userDto);

    IPage getUsersWithRolePage(Page page, UserDTO userDTO);

    R<Boolean> lockUser(String username);

    R changePassword(UserDTO userDto);

    R checkPassword(String password);

    R<Boolean> updateUserInfo(UserDTO userDto);
}

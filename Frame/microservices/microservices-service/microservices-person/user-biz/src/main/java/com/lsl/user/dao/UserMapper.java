package com.lsl.user.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.user.api.dto.UserDTO;
import com.lsl.user.api.entity.User;
import com.lsl.user.api.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    void insert(User user);

    void removeAllByUserIds(List<Long> userIds);

    void updateById(User user);

    User queryInfoByNameAndPhone(String username, String phone);

    User queryInfoByName(String username);

    UserVO queryInfoById(Long id);

    User query(User query);

    List<User> queryAllByUserIds(List<Long> userIds);

    IPage<UserVO> getUserVoPage(Page page, UserDTO userDTO);

    UserVO getUserVoByUsername(String username);

    void updatePassword(String password, Long userId);
}

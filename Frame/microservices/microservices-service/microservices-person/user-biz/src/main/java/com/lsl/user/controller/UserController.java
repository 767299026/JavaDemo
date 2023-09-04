/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.lsl.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.constant.Constant;
import com.lsl.constant.ErrorCodes;
import com.lsl.security.annotation.Inner;
import com.lsl.security.utils.SpringSecurityUtils;
import com.lsl.user.api.dto.UserDTO;
import com.lsl.user.api.entity.User;
import com.lsl.user.service.UserService;
import com.lsl.utils.MsgUtil;
import com.lsl.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping(value = {"/info/query"})
	public R info(@RequestParam(required = false) String username, @RequestParam(required = false) String phone) {
		User user = userService.queryInfo(username, phone);
		if (user == null) {
			return R.failed(MsgUtil.getMessage(ErrorCodes.USER_USERINFO_EMPTY, username));
		}
		return R.ok(userService.findUserInfo(user));
	}

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public R info() {
		String username = SpringSecurityUtils.getUser().getUsername();
		User user = userService.queryUserByUsername(username);
		if (user == null) {
			return R.failed(MsgUtil.getMessage(ErrorCodes.USER_QUERY_ERROR));
		}
		return R.ok(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/details/{id}")
	public R user(@PathVariable Long id) {
		return R.ok(userService.selectUserVoById(id));
	}

	/**
	 * 查询用户信息
	 *
	 * @param query 查询条件
	 * @return 不为空返回用户名
	 */
	@Inner(value = false)
	@GetMapping("/details")
	public R getDetails(User query) {
		User user = userService.query(query);
		return R.ok(user == null ? null : Constant.SUCCESS);
	}

	/**
	 * 删除用户信息
	 *
	 * @param ids ID
	 * @return R
	 */
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('user_del')")
	public R userDel(@RequestBody Long[] ids) {
		return R.ok(userService.deleteUserByIds(ids));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('user_add')")
	public R user(@RequestBody UserDTO userDto) {
		return R.ok(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('user_edit')")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
		return R.ok(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getUserPage(Page page, UserDTO userDTO) {
		return R.ok(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@PutMapping("/edit")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * 锁定指定用户
	 *
	 * @param username 用户名
	 * @return R
	 */
	@Inner
	@PutMapping("/lock/{username}")
	public R lockUser(@PathVariable String username) {
		return userService.lockUser(username);
	}

	@PutMapping("/password")
	public R password(@RequestBody UserDTO userDto) {
		String username = SpringSecurityUtils.getUser().getUsername();
		userDto.setUsername(username);
		return userService.changePassword(userDto);
	}

	@PostMapping("/check")
	public R check(String password) {
		return userService.checkPassword(password);
	}

}

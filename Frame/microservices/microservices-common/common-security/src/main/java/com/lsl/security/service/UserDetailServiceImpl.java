/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lsl.security.service;

import com.lsl.constant.CacheConstant;
import com.lsl.constant.SecurityConstant;
import com.lsl.security.vo.SecurityUser;
import com.lsl.utils.R;
import com.lsl.user.api.dto.UserDTO;
import com.lsl.user.api.dto.UserInfo;
import com.lsl.user.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 用户详细信息
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 用户名密码登录
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(CacheConstant.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (SecurityUser) cache.get(username).get();
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		R<UserInfo> result = remoteUserService.info(userDTO, SecurityConstant.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		if (cache != null) {
			cache.put(username, userDetails);
		}
		return userDetails;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}

/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jt.auth.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.auth.models.User;
import com.jwt.auth.service.UserService;
import com.jwt.auth.utils.SecurityUtils;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang
	 * .String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByUsername(username);
		Set<GrantedAuthority> authorities =
				Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

		// TODO Auto-generated method stub
		return UserPrincipal.builder().user(user).id(user.getId()).username(user.getUsername())
				.password(user.getPassword()).build();
	}

}

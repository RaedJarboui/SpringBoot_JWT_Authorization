/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
public class SecurityUtils {
	public static final String Role_PREFIX = "ROLE_";

	public static SimpleGrantedAuthority convertToAuthority(String role) {

		String formattedRole = role.startsWith(Role_PREFIX) ? role : Role_PREFIX + role;
		return new SimpleGrantedAuthority(formattedRole);
	}

}

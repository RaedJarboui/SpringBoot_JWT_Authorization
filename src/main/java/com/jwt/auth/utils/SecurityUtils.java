/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
public class SecurityUtils {
	public static final String Role_PREFIX = "ROLE_";
	public static final String AUTH_HEADER = "authorization";
	public static final String AUTH_TOKEN_HEADER = "Bearer";
	public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_HEADER + " ";

	public static SimpleGrantedAuthority convertToAuthority(String role) {

		String formattedRole = role.startsWith(Role_PREFIX) ? role : Role_PREFIX + role;
		return new SimpleGrantedAuthority(formattedRole);
	}

	public static String extractAuthTokenFromRequest(HttpServletRequest request) {

		String bearerToken = request.getHeader(AUTH_HEADER);
		if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

}

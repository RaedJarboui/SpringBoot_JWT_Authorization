/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.jwt.auth.security.UserPrincipal;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
public interface JwtProvider {
	String generateToken(UserPrincipal auth);

	Authentication getAuthentication(HttpServletRequest request);

	boolean isTokenValid(HttpServletRequest request);
}

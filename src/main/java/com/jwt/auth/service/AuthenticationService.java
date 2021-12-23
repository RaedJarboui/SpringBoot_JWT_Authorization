/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jwt.auth.models.User;
import com.jwt.auth.security.UserPrincipal;
import com.jwt.auth.security.jwt.JwtProvider;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Service
public class AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private JwtRefreshTokenService jwtRefreshTokenService;

	public User signInAndReturnJWT(User signInRequest) {

		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						signInRequest.getUsername(), signInRequest.getPassword()));

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		if (userPrincipal != null) {
			System.out.println("user sign in");
			System.out.println(userPrincipal);
		}
		else {
			System.out.println("user not sign in");

		}
		String jwt = jwtProvider.generateToken(userPrincipal);

		User signInUser = userPrincipal.getUser();
		System.out.println(signInUser.getUsername());
		System.out.println(signInUser.getId());

		signInUser.setAccessToken(jwt);
		signInUser.setRefreshToken(
				jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());

		return signInUser;
	}

}

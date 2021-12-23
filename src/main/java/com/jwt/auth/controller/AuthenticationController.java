/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth.models.User;
import com.jwt.auth.service.AuthenticationService;
import com.jwt.auth.service.JwtRefreshTokenService;
import com.jwt.auth.service.UserService;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtRefreshTokenService jwtRefreshTokenService;

	@PostMapping("sign-up") // api/authentication/sign-up
	public ResponseEntity<?> signUp(@RequestBody User user) {

		if (userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PostMapping("sign-in") //
	public ResponseEntity<?> signIn(@RequestBody User user) {

		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
	}

	@PostMapping("refresh-token") // api/authentication/refresh-token?token=
	public ResponseEntity<?> refreshToken(@RequestParam String token) {

		return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
	}

}

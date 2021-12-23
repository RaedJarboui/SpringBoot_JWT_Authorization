/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth.models.Role;
import com.jwt.auth.security.UserPrincipal;
import com.jwt.auth.service.AuthenticationService;
import com.jwt.auth.service.UserService;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationService authService;

	@PutMapping("change/{role}") // api/user/change/{role}
	public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable Role role) {
		// UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
		// .getAuthentication().getPrincipal(); ->recuperate current User

		userService.changeRole(role, userPrincipal.getUsername());

		return ResponseEntity.ok(true);
	}

}

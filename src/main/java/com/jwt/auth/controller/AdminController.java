/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth.service.UserService;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@RestController
@RequestMapping("api/admin") // pre-path
public class AdminController {
	@Autowired
	private UserService userService;

	@GetMapping("all") // api/admin/all
	public ResponseEntity<?> findAllUsers() {

		return ResponseEntity.ok(userService.findAllUsers());
	}
}

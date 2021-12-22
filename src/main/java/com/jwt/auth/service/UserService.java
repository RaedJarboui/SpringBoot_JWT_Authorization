/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.auth.models.Role;
import com.jwt.auth.models.User;
import com.jwt.auth.repository.UserRepository;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordencoder;

	public User saveUser(User user) {

		user.setPassword(passwordencoder.encode(user.getPassword()));
		user.setRole(Role.User);
		user.setCreateTime(LocalDate.now());
		return userRepository.save(user);
	}

	public User findByEmail(String email) {

		return userRepository.findByEmail(email);

	}

	public User findByUsername(String username) {

		return userRepository.findByUsername(username);

	}

	@Transactional
	public void UpdateUser(String username, Role role) {

		userRepository.updateUser(username, role);
	}

	public List<User> finAllUsers() {

		return userRepository.findAll();
	}
}

/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	PasswordEncoder passwordEncoder;

	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.User);
		user.setCreateTime(LocalDate.now());

		return userRepository.save(user);
	}

	public Optional<User> findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Transactional // Transactional is required when executing an update/delete query.
	public void changeRole(Role newRole, String username) {

		userRepository.updateUserRole(username, newRole);
	}

	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	public User findSingleUser(Long id) {

		return userRepository.findById(id).get();
	}
}

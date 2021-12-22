/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.auth.models.Role;
import com.jwt.auth.models.User;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByUsername(String username);

	@Modifying
	@Query("Update User u set u.role = :role where u.username = :username  ")
	void updateUser(@Param("username") String username, @Param("role") Role role);

}

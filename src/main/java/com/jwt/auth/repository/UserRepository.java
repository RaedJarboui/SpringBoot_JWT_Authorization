/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.repository;

import java.util.Optional;

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

	// findBy+fieldName

	Optional<User> findByUsername(String username);

	@Modifying
	@Query("update User set role = :role where username = :username")
	void updateUserRole(@Param("username") String username, @Param("role") Role role);

}

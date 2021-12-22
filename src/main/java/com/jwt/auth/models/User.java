/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Table(name = "users")
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "username")
	private String username;
	@Column(name = "created_at")
	private LocalDate createTime;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

}

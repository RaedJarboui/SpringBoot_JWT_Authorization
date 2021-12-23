/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.auth.models.JwtRefreshToken;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String> {

}

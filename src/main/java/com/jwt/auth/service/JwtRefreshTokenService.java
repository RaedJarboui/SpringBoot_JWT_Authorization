/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jwt.auth.models.JwtRefreshToken;
import com.jwt.auth.models.User;
import com.jwt.auth.repository.JwtRefreshTokenRepository;
import com.jwt.auth.repository.UserRepository;
import com.jwt.auth.security.UserPrincipal;
import com.jwt.auth.security.jwt.JwtProvider;
import com.jwt.auth.utils.SecurityUtils;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Service
public class JwtRefreshTokenService {
	@Value("${app.jwt.refresh-expiration-in-ms}")
	private Long REFRESH_EXPIRATION_IN_MS;

	@Autowired
	private JwtRefreshTokenRepository jwtRefreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtProvider jwtProvider;

	public JwtRefreshToken createRefreshToken(Long userId) {

		JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();

		jwtRefreshToken.setTokenId(UUID.randomUUID().toString());
		jwtRefreshToken.setUserId(userId);
		jwtRefreshToken.setCreateDate(LocalDateTime.now());
		jwtRefreshToken.setExpirationDate(
				LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

		return jwtRefreshTokenRepository.save(jwtRefreshToken);
	}

	public User generateAccessTokenFromRefreshToken(String refreshTokenId) {

		JwtRefreshToken jwtRefreshToken =
				jwtRefreshTokenRepository.findById(refreshTokenId).orElseThrow();

		if (jwtRefreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("JWT refresh token is not valid.");
		}

		User user = userRepository.findById(jwtRefreshToken.getUserId()).orElseThrow();

		UserPrincipal userPrincipal = UserPrincipal.builder().id(user.getId())
				.username(user.getUsername()).password(user.getPassword())
				.authorities(Set.of(SecurityUtils.convertToAuthority(user.getRole().name())))
				.build();

		String accessToken = jwtProvider.generateToken(userPrincipal);

		user.setAccessToken(accessToken);
		user.setRefreshToken(refreshTokenId);

		return user;
	}

}

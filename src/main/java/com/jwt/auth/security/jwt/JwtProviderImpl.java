/*
 * Copyright (C) TALYS ™ - All Rights Reserved Unauthorized copying of this file, via any medium is
 * strictly prohibited Proprietary and confidential
 */
package com.jwt.auth.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jwt.auth.security.UserPrincipal;
import com.jwt.auth.utils.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * {@link } class.
 *
 * @author hp
 * @since 0.1.0
 */
@Component
public class JwtProviderImpl implements JwtProvider {
	@Value("${app.jwt.secret}")
	private String jWT_SECRET;

	@Value("${app.jwt.expiration-in-ms}")
	private Long jWT_EXPIRATION_IN_MS;

	public String generateToken(UserPrincipal auth) {

		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		Key key = Keys.hmacShaKeyFor(jWT_SECRET.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder().setSubject(auth.getUsername()).claim("roles", authorities)
				.claim("userId", auth.getId())
				.setExpiration(new Date(System.currentTimeMillis() + jWT_EXPIRATION_IN_MS))
				.signWith(key, io.jsonwebtoken.SignatureAlgorithm.HS512).compact();

	}

	public Authentication getAuthentication(HttpServletRequest request) {

		Claims claims = extractClaims(request);
		if (claims == null) {
			return null;
		}
		String username = claims.getSubject();
		Long userId = claims.get("userId", Long.class);
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertToAuthority).collect(Collectors.toSet());
		UserDetails userDetails = UserPrincipal.builder().username(username)
				.authorities(authorities).id(userId).build();
		if (username == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

	public boolean isTokenValid(HttpServletRequest request) {

		Claims claims = extractClaims(request);
		if (claims == null) {
			return false;
		}
		if (claims.getExpiration().before(new Date())) {
			return false;
		}
		return true;
	}

	private Claims extractClaims(HttpServletRequest request) {

		String token = SecurityUtils.extractAuthTokenFromRequest(request);
		if (token == null) {
			return null;
		}
		Key key = Keys.hmacShaKeyFor(jWT_SECRET.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

}
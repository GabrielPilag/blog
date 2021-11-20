package com.gabriel.blog.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifier extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");//Obtengo el header que voy a querer manejar, en este caso contiene el token
		
		if ( Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);//Se rechaza la autorización porque esta vacio o es nulo o no empieza con "Bearer "
			return;
		}
		
		String token = authorizationHeader.replace("Bearer ", "");
		
		try {

			String secretKey = "securesecuresecuresecuresecuresecuresecure";
			Jws<Claims> claimsJws = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
					.build()
					.parseClaimsJws(token);
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			var authorities =  (List<Map<String,String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthority = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
			Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, simpleGrantedAuthority);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (JwtException e) {
			throw new IllegalStateException(String.format("Token %s cannot be truest", token) );
		}
		filterChain.doFilter(request, response);
	}

}

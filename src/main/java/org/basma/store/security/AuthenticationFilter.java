package org.basma.store.security;

import org.basma.store.SpringApplicationContext;
import org.basma.store.requests.UserLoginRequest;
import org.basma.store.services.UserService;
import org.basma.store.shared.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

  

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	  @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
	        
	        String userName = ((User) auth.getPrincipal()).getUsername(); 
	        
            UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
	        
	        UserDto userDto = userService.getUser(userName);
	        
//	        String token = Jwts.builder()
//	                .setSubject(userName)
//	                .claim("id", userDto.getUserId())
//	                .claim("name", userDto.getPrenomUser() + " " + userDto.getNomUser())
//	                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//	                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
//	                .compact();
	        
	        String jwt= JWT.create()
	                .withIssuer(req.getRequestURI())
	                .withClaim("id", userDto.getUserId())
	                .withClaim("name", userDto.getPrenomUser() + " " + userDto.getNomUser())
	                .withClaim("roles",userDto.getRoleUser())
	                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
	                .sign(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET));
	        res.addHeader(SecurityConstants.HEADER_STRING,jwt);
	       
//	        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
//	        res.addHeader("user_id", userDto.getUserId());
//	        
//	        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ userDto.getUserId() + "\"}");

	    }  
}

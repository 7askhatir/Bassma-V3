package org.basma.store.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.basma.store.shared.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Jwts;


public class AuthorizationFilter extends BasicAuthenticationFilter {
    
    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
     } 
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        System.out.println("aaaaaa");
        
        String jwtToken = req.getHeader(SecurityConstants.HEADER_STRING);
        if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
        	chain.doFilter(req, res);
            return;
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET)).build();
        String jwt = jwtToken.substring(SecurityConstants.TOKEN_PREFIX.length());
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String roles = decodedJWT.getClaims().get("roles").asString();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roles));
        UserDto utilisateur = new UserDto();
        utilisateur.setUserId(decodedJWT.getClaims().get("id").asString());
        utilisateur.setNomUser(decodedJWT.getClaims().get("name").asString());
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(utilisateur, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(user);
        chain.doFilter(req, res);
    }   
    
    
    
}

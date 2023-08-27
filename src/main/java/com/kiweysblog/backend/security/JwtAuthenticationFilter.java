package com.kiweysblog.backend.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String username;
        if (requestToken != null && requestToken.startsWith("Bearer")){
            String token = requestToken.substring(7);
            try {
                username = jwtTokenHelper.getUsernameFromToken(token);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails =  this.userDetailsService.loadUserByUsername(username);
                    if(this.jwtTokenHelper.validateToken(token, userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    }
                } else {
                    System.out.println("Username: null or Context: !null");
                }
            } catch (IllegalArgumentException exception){
                System.out.println("Unable to get JWT Token: Illegal Argument");
            } catch (ExpiredJwtException exception){
                System.out.println("JWT Token has expired");
            } catch (MalformedJwtException exception){
                System.out.println("Malformed JWT Token");
            }
        } else {
            System.out.println("JWT Token does not begin with 'Bearer'");
        }
        filterChain.doFilter(request, response);
    }
}

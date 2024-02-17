package com.aj.blog.jwt;

import com.aj.blog.jwt.JWTService;
import com.aj.blog.security.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AppUserDetailsService userDetailsService;
    private final JWTService jwtService;

    public JWTAuthenticationFilter(AppUserDetailsService userDetailsService, JWTService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("in Jwt filter");
        final String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userName = null;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        userName = jwtService.extractUsername(jwtToken);
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            System.out.println("username: "+userName);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            System.out.println("userdetails: "+userDetails);
            if(jwtService.isTokenValid(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("out Jwt filter");
            }
        }
        filterChain.doFilter(request, response);
    }
}

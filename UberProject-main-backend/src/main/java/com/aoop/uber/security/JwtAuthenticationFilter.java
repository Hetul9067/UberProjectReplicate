package com.aoop.uber.security;

import com.aoop.uber.model.passenger.JwtTokenBlackList;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private JwtTokenBlackList tokenBlacklist;

    public JwtAuthenticationFilter(JwtTokenBlackList jwtTokenBlackList){
        this.tokenBlacklist = tokenBlacklist;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //Authorization

        String requestHeader = request.getHeader("Authorization");
        //Bearer 2352345235sdfrsfgsdfsdf
        logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {

                username = this.jwtHelper.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
            logger.info("Invalid Header Value !! ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }


        }

        if ( !tokenBlacklist.isBlacklisted(token)) {
            // Token is valid and not blacklisted
            // Proceed with the request
            filterChain.doFilter(request, response);
        } else {
            // Token is invalid or blacklisted
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
//        filterChain.doFilter(request, response);


    }



//    public JwtTokenFilter(JwtTokenBlackList tokenBlacklist) {
//        this.tokenBlacklist = tokenBlacklist;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = extractToken(request);
//        if (token != null && !tokenBlacklist.isBlacklisted(token)) {
//            // Token is valid and not blacklisted
//            // Proceed with the request
//            filterChain.doFilter(request, response);
//        } else {
//            // Token is invalid or blacklisted
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//    }

//    private String extractToken(HttpServletRequest request) {
//        // Get the Authorization header from the request
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        // Check if the header is null or doesn't start with "Bearer "
//        if (header == null || !header.startsWith("Bearer ")) {
//            return null; // Invalid token format
//        }
//
//        // Extract the token part (remove "Bearer " prefix)
//        return header.substring(7);
//    }
}
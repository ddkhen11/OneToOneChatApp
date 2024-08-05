package com.danielkhen.websocket.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * Filter for JWT-based authentication.
 * This filter intercepts incoming requests, validates the JWT token if present,
 * and sets the authentication in the SecurityContext if the token is valid.
 */
@Component
@RequiredArgsConstructor
// TODO: setup caching
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Performs the filtering process for each request to validate JWT and set authentication.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain for additional filters to be applied.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs during the processing of the request.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        // If there's no Authorization header or it doesn't start with "Bearer ", proceed with the filter chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract the JWT token from the Authorization header
            final String jwt = authHeader.substring(7);
            final String nickName = jwtTokenUtil.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // If the token contains a username and there's no existing authentication, proceed with validation
            if (nickName != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(nickName);

                // If the token is valid for the user, create and set a new authentication token
                if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // Handle any exceptions that occur during the filtering process
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
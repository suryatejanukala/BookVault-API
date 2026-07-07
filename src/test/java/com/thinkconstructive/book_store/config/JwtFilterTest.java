package com.thinkconstructive.book_store.config;

import com.thinkconstructive.book_store.service.JWTService;
import com.thinkconstructive.book_store.service.impl.UserInfoUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JwtFilter Tests")
class JwtFilterTest {

    @Mock
    private JWTService jwtService;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserInfoUserDetailsServiceImpl userDetailsService;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtFilter jwtFilter;

    private StringWriter responseWriter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        responseWriter = new StringWriter();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: Valid Bearer token sets SecurityContext authentication and proceeds with filter chain
     * - Expected: Authentication is set in SecurityContext and filterChain.doFilter is called
     * - Coverage Target: Happy path - valid token flow, lines 36-56
     */
    @Test
    @DisplayName("Should authenticate with valid Bearer token")
    void testDoFilterValidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid.jwt.token");
        when(jwtService.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(applicationContext.getBean(UserInfoUserDetailsServiceImpl.class)).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.validateToken("valid.jwt.token", userDetails)).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: No Authorization header, passes through without authentication
     * - Expected: filterChain.doFilter called, no authentication set
     * - Coverage Target: Null auth header branch, line 35
     */
    @Test
    @DisplayName("Should pass through when no Authorization header")
    void testDoFilterNoAuthHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: Authorization header without "Bearer " prefix, passes through
     * - Expected: filterChain.doFilter called, no authentication set
     * - Coverage Target: Non-Bearer header branch, line 35
     */
    @Test
    @DisplayName("Should pass through when Authorization header is not Bearer")
    void testDoFilterNonBearerHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Basic dXNlcjpwYXNz");

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: Valid token but SecurityContext already has authentication, skips re-auth
     * - Expected: Existing authentication preserved, filterChain.doFilter called
     * - Coverage Target: Already authenticated branch, line 50
     */
    @Test
    @DisplayName("Should skip re-authentication when already authenticated")
    void testDoFilterAlreadyAuthenticated() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid.jwt.token");
        when(jwtService.extractUsername("valid.jwt.token")).thenReturn("testuser");

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication existingAuth = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(existingAuth);
        SecurityContextHolder.setContext(securityContext);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(applicationContext, never()).getBean(UserInfoUserDetailsServiceImpl.class);
        verify(filterChain).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: ExpiredJwtException caught, returns 401 with "JWT token has expired"
     * - Expected: Response status 401, body "JWT token has expired", filter chain not continued
     * - Coverage Target: ExpiredJwtException catch block, lines 39-42
     */
    @Test
    @DisplayName("Should return 401 when token is expired")
    void testDoFilterExpiredToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer expired.jwt.token");
        when(jwtService.extractUsername("expired.jwt.token"))
                .thenThrow(new ExpiredJwtException(null, null, "Token expired"));
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        assertTrue(responseWriter.toString().contains("JWT token has expired"));
        verify(filterChain, never()).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: SignatureException caught, returns 401 with "Invalid JWT signature"
     * - Expected: Response status 401, body "Invalid JWT signature", filter chain not continued
     * - Coverage Target: SignatureException catch block, lines 43-46
     */
    @Test
    @DisplayName("Should return 401 when token has invalid signature")
    void testDoFilterInvalidSignature() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer bad.signature.token");
        when(jwtService.extractUsername("bad.signature.token"))
                .thenThrow(new SignatureException("Bad signature"));
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        assertTrue(responseWriter.toString().contains("Invalid JWT signature"));
        verify(filterChain, never()).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: Generic exception caught, returns 401 with "Invalid JWT token"
     * - Expected: Response status 401, body "Invalid JWT token", filter chain not continued
     * - Coverage Target: Generic exception catch block, lines 47-50
     */
    @Test
    @DisplayName("Should return 401 when token is invalid (generic exception)")
    void testDoFilterInvalidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer malformed.token");
        when(jwtService.extractUsername("malformed.token"))
                .thenThrow(new RuntimeException("Malformed"));
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        assertTrue(responseWriter.toString().contains("Invalid JWT token"));
        verify(filterChain, never()).doFilter(request, response);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: doFilterInternal
     * - Scenario: Valid token but validation returns false
     * - Expected: No authentication set in SecurityContext, filter chain continues
     * - Coverage Target: validateToken returns false branch, line 53
     */
    @Test
    @DisplayName("Should not authenticate when token validation fails")
    void testDoFilterTokenValidationFails() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid.jwt.token");
        when(jwtService.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(applicationContext.getBean(UserInfoUserDetailsServiceImpl.class)).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.validateToken("valid.jwt.token", userDetails)).thenReturn(false);

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }
}

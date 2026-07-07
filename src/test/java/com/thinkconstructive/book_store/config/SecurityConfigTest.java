package com.thinkconstructive.book_store.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtFilter jwtFilter;

    @InjectMocks
    private SecurityConfig securityConfig;

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: authenticationProvider()
     * - Scenario: Bean is DaoAuthenticationProvider with correct UserDetailsService and PasswordEncoder
     * - Expected: Returns DaoAuthenticationProvider instance
     * - Coverage Target: authenticationProvider() method
     */
    @Test
    @DisplayName("Should create DaoAuthenticationProvider bean with correct configuration")
    void testAuthenticationProviderBean() {
        AuthenticationProvider provider = securityConfig.authenticationProvider();

        assertNotNull(provider);
        assertInstanceOf(DaoAuthenticationProvider.class, provider);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: passwordEncoder()
     * - Scenario: Returns BCryptPasswordEncoder instance
     * - Expected: Returns non-null BCryptPasswordEncoder
     * - Coverage Target: passwordEncoder() method
     */
    @Test
    @DisplayName("Should create BCryptPasswordEncoder bean")
    void testPasswordEncoderBean() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();

        assertNotNull(encoder);
        assertInstanceOf(BCryptPasswordEncoder.class, encoder);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: authenticationManager(AuthenticationConfiguration)
     * - Scenario: Returns valid AuthenticationManager from AuthenticationConfiguration
     * - Expected: Returns the AuthenticationManager from configuration
     * - Coverage Target: authenticationManager() method
     */
    @Test
    @DisplayName("Should create AuthenticationManager bean from AuthenticationConfiguration")
    void testAuthenticationManagerBean() throws Exception {
        AuthenticationConfiguration authConfig = mock(AuthenticationConfiguration.class);
        AuthenticationManager expectedManager = mock(AuthenticationManager.class);
        when(authConfig.getAuthenticationManager()).thenReturn(expectedManager);

        AuthenticationManager result = securityConfig.authenticationManager(authConfig);

        assertNotNull(result);
        assertSame(expectedManager, result);
        verify(authConfig).getAuthenticationManager();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: securityFilterChain(HttpSecurity)
     * - Scenario: SecurityFilterChain is created with CSRF disabled, stateless sessions,
     *             URL authorization rules, httpBasic, and JWT filter
     * - Expected: SecurityFilterChain bean is built successfully
     * - Coverage Target: securityFilterChain() method - all lines including lambda configurers
     */
    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Should create SecurityFilterChain with correct authorization rules")
    void testSecurityFilterChainBean() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class);
        DefaultSecurityFilterChain expectedChain = mock(DefaultSecurityFilterChain.class);

        // Mock csrf - invoke the lambda to get coverage
        CsrfConfigurer<HttpSecurity> csrfConfigurer = mock(CsrfConfigurer.class);
        when(http.csrf(any())).thenAnswer(invocation -> {
            var customizer = invocation.getArgument(0, org.springframework.security.config.Customizer.class);
            customizer.customize(csrfConfigurer);
            return http;
        });

        // Mock sessionManagement - invoke the lambda
        SessionManagementConfigurer<HttpSecurity> sessionConfigurer = mock(SessionManagementConfigurer.class);
        when(http.sessionManagement(any())).thenAnswer(invocation -> {
            var customizer = invocation.getArgument(0, org.springframework.security.config.Customizer.class);
            customizer.customize(sessionConfigurer);
            return http;
        });
        when(sessionConfigurer.sessionCreationPolicy(any())).thenReturn(sessionConfigurer);

        // Mock authorizeHttpRequests - invoke the lambda
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRegistry =
                mock(AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry.class);
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl =
                mock(AuthorizeHttpRequestsConfigurer.AuthorizedUrl.class);

        when(http.authorizeHttpRequests(any())).thenAnswer(invocation -> {
            var customizer = invocation.getArgument(0, org.springframework.security.config.Customizer.class);
            customizer.customize(authRegistry);
            return http;
        });
        when(authRegistry.requestMatchers(any(String[].class))).thenReturn(authorizedUrl);
        when(authRegistry.requestMatchers(any(org.springframework.http.HttpMethod.class), any(String[].class))).thenReturn(authorizedUrl);
        when(authRegistry.anyRequest()).thenReturn(authorizedUrl);
        when(authorizedUrl.permitAll()).thenReturn(authRegistry);
        when(authorizedUrl.hasAnyRole(any(String[].class))).thenReturn(authRegistry);
        when(authorizedUrl.hasRole(anyString())).thenReturn(authRegistry);
        when(authorizedUrl.authenticated()).thenReturn(authRegistry);

        // Mock httpBasic
        when(http.httpBasic(any())).thenReturn(http);

        // Mock addFilterBefore
        when(http.addFilterBefore(any(), any())).thenReturn(http);

        // Mock build
        when(http.build()).thenReturn(expectedChain);

        // when
        SecurityFilterChain result = securityConfig.securityFilterChain(http);

        // then
        assertNotNull(result);
        assertSame(expectedChain, result);
        verify(http).csrf(any());
        verify(http).sessionManagement(any());
        verify(http).authorizeHttpRequests(any());
        verify(http).httpBasic(any());
        verify(http).addFilterBefore(eq(jwtFilter), any());
        verify(http).build();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: passwordEncoder()
     * - Scenario: PasswordEncoder can encode and match passwords
     * - Expected: Encoded password matches original via matches()
     * - Coverage Target: passwordEncoder functionality verification
     */
    @Test
    @DisplayName("Should encode passwords with BCrypt")
    void testPasswordEncoderEncodes() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        String rawPassword = "testPassword123";

        String encoded = encoder.encode(rawPassword);

        assertNotNull(encoded);
        assertNotEquals(rawPassword, encoded);
        assertTrue(encoder.matches(rawPassword, encoded));
    }
}

package com.thinkconstructive.book_store.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("JWTServiceImpl Tests")
class JWTServiceImplTest {

    private JWTServiceImpl jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        jwtService = new JWTServiceImpl();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: generateToken
     * - Scenario: Generate valid JWT with correct subject (username) and expiration
     * - Expected: Non-null, non-empty token string
     * - Coverage Target: generateToken method, lines covering Jwts.builder() chain
     */
    @Test
    @DisplayName("Should generate a valid JWT token")
    void testGenerateTokenSuccess() {
        String token = jwtService.generateToken("testuser");

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: extractUsername
     * - Scenario: Extract correct username from a valid token
     * - Expected: Returns the username that was used to generate the token
     * - Coverage Target: extractUsername, extractClaim, extractAllClaims, getKey methods
     */
    @Test
    @DisplayName("Should extract correct username from valid token")
    void testExtractUsernameSuccess() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        String extracted = jwtService.extractUsername(token);

        assertEquals(username, extracted);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: validateToken
     * - Scenario: Valid token with matching username and non-expired
     * - Expected: Returns true
     * - Coverage Target: validateToken method, isTokenExpired, extractExpiration
     */
    @Test
    @DisplayName("Should validate token successfully when username matches and not expired")
    void testValidateTokenSuccess() {
        String username = "testuser";
        String token = jwtService.generateToken(username);
        when(userDetails.getUsername()).thenReturn(username);

        boolean result = jwtService.validateToken(token, userDetails);

        assertTrue(result);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: validateToken
     * - Scenario: Username in token doesn't match UserDetails username
     * - Expected: Returns false
     * - Coverage Target: validateToken false branch for username mismatch
     */
    @Test
    @DisplayName("Should return false when token username doesn't match UserDetails")
    void testValidateTokenUsernameMismatch() {
        String token = jwtService.generateToken("user1");
        when(userDetails.getUsername()).thenReturn("user2");

        boolean result = jwtService.validateToken(token, userDetails);

        assertFalse(result);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: validateToken
     * - Scenario: Expired token should fail validation
     * - Expected: Throws ExpiredJwtException during validation
     * - Coverage Target: isTokenExpired method, expiration check branch
     */
    @Test
    @DisplayName("Should throw ExpiredJwtException when token is expired")
    void testValidateTokenExpired() throws Exception {
        String username = "testuser";
        // Generate an expired token using reflection to access the secret key
        Field secretKeyField = JWTServiceImpl.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        String secret = (String) secretKeyField.get(jwtService);

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String expiredToken = Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis() - 60000 * 60))
                .expiration(new Date(System.currentTimeMillis() - 60000 * 30))
                .signWith(key)
                .compact();

        assertThrows(ExpiredJwtException.class, () -> jwtService.validateToken(expiredToken, userDetails));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: extractUsername
     * - Scenario: Malformed/tampered token throws exception
     * - Expected: Exception thrown
     * - Coverage Target: extractAllClaims error path
     */
    @Test
    @DisplayName("Should throw exception when extracting username from invalid token")
    void testExtractUsernameInvalidToken() {
        assertThrows(Exception.class, () -> jwtService.extractUsername("invalid.token.string"));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: extractUsername
     * - Scenario: Expired token throws ExpiredJwtException
     * - Expected: ExpiredJwtException thrown
     * - Coverage Target: extractAllClaims with expired token
     */
    @Test
    @DisplayName("Should throw ExpiredJwtException when extracting from expired token")
    void testExtractUsernameExpiredToken() throws Exception {
        Field secretKeyField = JWTServiceImpl.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        String secret = (String) secretKeyField.get(jwtService);

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String expiredToken = Jwts.builder()
                .subject("testuser")
                .issuedAt(new Date(System.currentTimeMillis() - 60000 * 60))
                .expiration(new Date(System.currentTimeMillis() - 60000 * 30))
                .signWith(key)
                .compact();

        assertThrows(ExpiredJwtException.class, () -> jwtService.extractUsername(expiredToken));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: generateToken
     * - Scenario: Verify token has correct expiration (~30 minutes from now)
     * - Expected: Token expiration is approximately 30 minutes in the future
     * - Coverage Target: Token expiration boundary, extractExpiration path
     */
    @Test
    @DisplayName("Should generate token with ~30 minute expiration")
    void testTokenExpirationBoundary() throws Exception {
        long beforeGeneration = System.currentTimeMillis();
        String token = jwtService.generateToken("testuser");
        long afterGeneration = System.currentTimeMillis();

        // Extract expiration via reflection to access private method
        Field secretKeyField = JWTServiceImpl.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        String secret = (String) secretKeyField.get(jwtService);

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        long expectedMin = beforeGeneration + 1000 * 60 * 30;
        long expectedMax = afterGeneration + 1000 * 60 * 30;

        assertTrue(expiration.getTime() >= expectedMin - 1000);
        assertTrue(expiration.getTime() <= expectedMax + 1000);
    }
}

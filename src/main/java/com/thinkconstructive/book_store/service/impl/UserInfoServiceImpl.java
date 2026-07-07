package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.entity.UserInfo;
import com.thinkconstructive.book_store.exception.UserAlreadyExistsException;
import com.thinkconstructive.book_store.exception.UserNotFoundException;
import com.thinkconstructive.book_store.mapper.UserInfoMapper;
import com.thinkconstructive.book_store.repository.UserInfoRepository;
import com.thinkconstructive.book_store.service.JWTService;
import com.thinkconstructive.book_store.service.UserInfoService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public JWTService jwtService;

    @Override
    public ResponseStructure createUser(UserInfoDto userInfoDto) {
        log.info("Creating user: {}", userInfoDto.userName());
        if (userInfoRepository.findByUserName(userInfoDto.userName()).isPresent()) {
            throw new UserAlreadyExistsException(userInfoDto.userName());
        }
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        log.info("User created successfully: {}", userInfoDto.userName());
        return ResponseStructure.builder()
                .status(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(UserInfoMapper.toDto(userInfo))
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public ResponseStructure getUserInfo(UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfoDto.userName(), userInfoDto.password()));
        if (authentication.isAuthenticated()) {
            log.info("Authentication successful for user: {}", userInfoDto.userName());
            String token = jwtService.generateToken(userInfoDto.userName());
            return ResponseStructure.builder()
                    .status(HttpStatus.OK.value())
                    .message("Authentication successful")
                    .data(token)
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        log.warn("Authentication failed for user: {}", userInfoDto.userName());
        throw new UserNotFoundException("Invalid credentials for user: " + userInfoDto.userName());
    }
}

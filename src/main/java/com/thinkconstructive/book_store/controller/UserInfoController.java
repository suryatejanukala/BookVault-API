package com.thinkconstructive.book_store.controller;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.service.UserInfoService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure> createUserInfo(@Valid @RequestBody UserInfoDto userInfoDto) {
        log.info("POST /user-info/register - Registering user: {}", userInfoDto.userName());
        return new ResponseEntity<>(userInfoService.createUser(userInfoDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure> getUserInfo(@Valid @RequestBody UserInfoDto userInfoDto) {
        log.info("POST /user-info/login - Login attempt for user: {}", userInfoDto.userName());
        return new ResponseEntity<>(userInfoService.getUserInfo(userInfoDto), HttpStatus.OK);
    }
}

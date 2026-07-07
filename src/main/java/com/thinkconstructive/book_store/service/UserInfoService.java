package com.thinkconstructive.book_store.service;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.util.ResponseStructure;

public interface UserInfoService {
    ResponseStructure createUser(UserInfoDto userInfoDto);
    ResponseStructure getUserInfo(UserInfoDto userInfoDto);
}

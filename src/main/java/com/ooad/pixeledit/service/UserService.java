package com.ooad.pixeledit.service;

import com.ooad.pixeledit.dto.UserDto;
import com.ooad.pixeledit.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}

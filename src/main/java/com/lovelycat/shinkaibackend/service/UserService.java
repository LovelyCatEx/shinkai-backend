package com.lovelycat.shinkaibackend.service;

import com.lovelycat.shinkaibackend.entity.User;

public interface UserService {
    User getUserByUsername(String username);
}

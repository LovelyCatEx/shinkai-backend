package com.lovelycat.shinkaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lovelycat.shinkaibackend.entity.User;
import com.lovelycat.shinkaibackend.mapper.UserMapper;
import com.lovelycat.shinkaibackend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在或已注销");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }
}

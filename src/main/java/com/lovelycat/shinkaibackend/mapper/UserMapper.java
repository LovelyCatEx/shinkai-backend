package com.lovelycat.shinkaibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lovelycat.shinkaibackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

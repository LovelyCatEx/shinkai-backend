package com.lovelycat.shinkaibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lovelycat.shinkaibackend.entity.Creation;

import java.util.List;

public interface CreationService extends IService<Creation> {
    List<Creation> getAllCreations();
}

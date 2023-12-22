package com.lovelycat.shinkaibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lovelycat.shinkaibackend.entity.CreationSection;

import java.util.List;

public interface CreationSectionService extends IService<CreationSection> {
    List<CreationSection> getCreationSections(Long creationId);
}

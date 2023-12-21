package com.lovelycat.shinkaibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lovelycat.shinkaibackend.entity.CreationCharacter;

import java.util.List;

public interface CreationCharacterService extends IService<CreationCharacter> {

    List<CreationCharacter> getCreationCharacters(Long creationId);
}

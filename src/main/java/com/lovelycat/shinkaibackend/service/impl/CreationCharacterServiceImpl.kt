package com.lovelycat.shinkaibackend.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.Creation
import com.lovelycat.shinkaibackend.entity.CreationCharacter
import com.lovelycat.shinkaibackend.mapper.CreationCharacterMapper
import com.lovelycat.shinkaibackend.service.CreationCharacterService
import com.lovelycatv.arkcache.MultiCacheTemplate
import com.lovelycatv.arkcache.toList
import org.springframework.stereotype.Service

@Service
class CreationCharacterServiceImpl: ServiceImpl<CreationCharacterMapper, CreationCharacter>(), CreationCharacterService {
    @Suppress("UNCHECKED_CAST")
    private var creationCharacterMultiCacheTemplate: MultiCacheTemplate<CreationCharacter>? = null
        get() {
            if (field == null) {
                field = ShinkaiBackendApplication.cacheTemplateContainer.getTemplate(
                    CreationCharacter::class.java
                ) as MultiCacheTemplate<CreationCharacter>
            }
            return field
        }


    override fun getCreationCharacters(creationId: Long): MutableList<CreationCharacter?>
        = creationCharacterMultiCacheTemplate!!.getOne(0, creationId) as MutableList<CreationCharacter?>
}
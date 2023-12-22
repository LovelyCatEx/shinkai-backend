package com.lovelycat.shinkaibackend.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.CreationCharacter
import com.lovelycat.shinkaibackend.entity.CreationSection
import com.lovelycat.shinkaibackend.mapper.CreationSectionMapper
import com.lovelycat.shinkaibackend.service.CreationSectionService
import com.lovelycatv.arkcache.MultiCacheTemplate
import com.lovelycatv.arkcache.toList
import org.springframework.stereotype.Service

@Service
class CreationSectionServiceImpl: ServiceImpl<CreationSectionMapper, CreationSection>(), CreationSectionService {
    @Suppress("UNCHECKED_CAST")
    private var creationCharacterMultiCacheTemplate: MultiCacheTemplate<CreationSection>? = null
        get() {
            if (field == null) {
                field = ShinkaiBackendApplication.cacheTemplateContainer.getTemplate(
                    CreationSection::class.java
                ) as MultiCacheTemplate<CreationSection>
            }
            return field
        }

    override fun getCreationSections(creationId: Long): MutableList<CreationSection?>
        = creationCharacterMultiCacheTemplate!!.getOne(0, creationId) as MutableList<CreationSection?>

}
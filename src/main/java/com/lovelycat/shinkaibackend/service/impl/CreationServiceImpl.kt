package com.lovelycat.shinkaibackend.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.Creation
import com.lovelycat.shinkaibackend.mapper.CreationMapper
import com.lovelycat.shinkaibackend.service.CreationService
import com.lovelycatv.arkcache.MultiCacheTemplate
import com.lovelycatv.arkcache.toList
import org.springframework.stereotype.Service

@Service
class CreationServiceImpl : ServiceImpl<CreationMapper?, Creation?>(), CreationService {
    @Suppress("UNCHECKED_CAST")
    private var creationMultiCacheTemplate: MultiCacheTemplate<Creation>? = null
        get() {
            if (field == null) {
                field = ShinkaiBackendApplication.cacheTemplateContainer.getTemplate(
                    Creation::class.java
                ) as MultiCacheTemplate<Creation>
            }
            return field
        }

    override fun getAllCreations(): MutableList<Creation?> {
        return creationMultiCacheTemplate!!.getOne(1) as MutableList<Creation?>
    }
}

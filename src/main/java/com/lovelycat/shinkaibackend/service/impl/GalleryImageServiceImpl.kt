package com.lovelycat.shinkaibackend.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.GalleryImage
import com.lovelycat.shinkaibackend.mapper.GalleryImageMapper
import com.lovelycat.shinkaibackend.service.GalleryImageService
import com.lovelycatv.arkcache.MultiCacheTemplate
import org.springframework.stereotype.Service

@Service
class GalleryImageServiceImpl: ServiceImpl<GalleryImageMapper, GalleryImage>(), GalleryImageService {
    @Suppress("UNCHECKED_CAST")
    private var creationCharacterMultiCacheTemplate: MultiCacheTemplate<GalleryImage>? = null
        get() {
            if (field == null) {
                field = ShinkaiBackendApplication.cacheTemplateContainer.getTemplate(
                    GalleryImage::class.java
                ) as MultiCacheTemplate<GalleryImage>
            }
            return field
        }

    override fun getAllGalleryImages(): MutableList<GalleryImage?>
        = creationCharacterMultiCacheTemplate?.getOne(0) as MutableList<GalleryImage?>

}
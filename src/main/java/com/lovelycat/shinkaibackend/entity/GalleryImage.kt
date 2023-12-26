package com.lovelycat.shinkaibackend.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("gallery")
data class GalleryImage(
    val id: Long? = null,
    val feature: String? = null,
    val title: String? = null
)
{}
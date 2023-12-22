package com.lovelycat.shinkaibackend.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("creation_sections")
data class CreationSection(
    @TableId(type = IdType.AUTO)
    val id: Long? = null,
    val cid: Long? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val type: Long? = null,
    val feature: String? = null,
    val content: String? = null
) {
}
package com.lovelycat.shinkaibackend.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@TableName("creations")
data class Creation(
    @TableId(type = IdType.AUTO)
    val id: Long? = null,
    val name: String? = null,
    val originName: String? = null,
    val description: String? = null,
    val descriptionShort: String? = null,
    val publishedTime: Date? = null,
    val feature: String? = null,
    val featureVertical: String? = null) {}

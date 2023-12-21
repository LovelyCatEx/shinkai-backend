package com.lovelycat.shinkaibackend.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("characters")
data class CreationCharacter(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var cid: Long? = null,
    var name: String? = null,
    var origin_name: String? = null,
    var description: String? = null,
    var avatar: String? = null,
    var performer: String? = null
) {}
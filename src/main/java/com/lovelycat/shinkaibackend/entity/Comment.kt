package com.lovelycat.shinkaibackend.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.text.SimpleDateFormat
import java.util.Date

@TableName("comments")
data class Comment(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var cid: Long? = null,
    var nickname: String? = null,
    var email: String? = null,
    var content: String? = null,
    var rates: Int? = null,
    var publishedTime: String? = null,
    var published: Boolean? = null

) {
    fun autoSetPublishedTime(): Comment = this.also { publishedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) }
}
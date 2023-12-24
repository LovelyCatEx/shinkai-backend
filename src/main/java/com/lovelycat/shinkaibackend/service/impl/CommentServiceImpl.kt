package com.lovelycat.shinkaibackend.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.Comment
import com.lovelycat.shinkaibackend.entity.CreationCharacter
import com.lovelycat.shinkaibackend.mapper.CommentMapper
import com.lovelycat.shinkaibackend.service.CommentService
import com.lovelycatv.arkcache.MultiCacheTemplate
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl: ServiceImpl<CommentMapper, Comment>(), CommentService {
    @Suppress("UNCHECKED_CAST")
    private var creationCharacterMultiCacheTemplate: MultiCacheTemplate<Comment>? = null
        get() {
            if (field == null) {
                field = ShinkaiBackendApplication.cacheTemplateContainer.getTemplate(
                    Comment::class.java
                ) as MultiCacheTemplate<Comment>
            }
            return field
        }

    override fun postComment(comment: Comment) {
        creationCharacterMultiCacheTemplate?.removeCache(0, comment.cid!!)
        this.save(comment)
    }

    override fun getCommentsByCreationId(creationId: Long): MutableList<Comment?>
        = creationCharacterMultiCacheTemplate?.getOne(0, creationId) as MutableList<Comment?>
}
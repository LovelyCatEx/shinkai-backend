package com.lovelycat.shinkaibackend.line

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.lovelycat.shinkaibackend.ShinkaiBackendApplication
import com.lovelycat.shinkaibackend.entity.Creation
import com.lovelycat.shinkaibackend.service.CreationService
import com.lovelycatv.arkcache.DataSourceProvider
import com.lovelycatv.arkcache.strategy.CacheStorageStrategy
import com.lovelycatv.arkcache.strategy.key.FixedCacheKey
import com.lovelycatv.arkcache.strategy.key.MutableCacheKey
import com.lovelycatv.arkcache.toList
import jakarta.annotation.Resource
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class ApplicationInitLine : CommandLineRunner {

    @Resource
    private var creationService: CreationService? = null

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        ShinkaiBackendApplication.cacheTemplateContainer.registerMultiTemplate(Creation::class.java) {
            val strategy = CacheStorageStrategy(0, MutableCacheKey("creation:?", object: MutableCacheKey.MutableKeyProvider<Iterable<Creation?>> {
                override fun provide(keyFormat: String, vararg args: Any): String
                    = keyFormat.replace("?", (args[0] as Long).toString())

                override fun provideForSetValue(keyFormat: String, data: Iterable<Creation?>): String
                    = keyFormat.replace("?", data.iterator().next()?.id.toString())
            }))
            val strategyAll = CacheStorageStrategy(1, FixedCacheKey<Iterable<Creation?>>("creations"))
            it.apply {
                addStrategy(strategy)
                addStrategy(strategyAll)
                it.customDataSource(object: DataSourceProvider<Iterable<Creation?>> {
                    override fun provide(
                        strategy: CacheStorageStrategy<Iterable<Creation?>>,
                        vararg args: Any?
                    ): Iterable<Creation?>? = when (strategy.id) {
                        0 -> creationService?.getById(args[0] as Long).toList()
                        1 -> creationService?.list(QueryWrapper<Creation>().orderByDesc("published_time"))
                        else -> TODO()
                    }
                })
            }
        }
    }
}

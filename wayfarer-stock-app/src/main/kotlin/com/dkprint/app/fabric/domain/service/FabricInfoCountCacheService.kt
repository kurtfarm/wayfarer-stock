package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service

@Service
class FabricInfoCountCacheService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    @Cacheable(value = ["fabricTotalCount"])
    fun getCachedTotalCount(): Long {
        return fabricInfoRepository.countAll()
    }

    @Caching(
        evict = [
            CacheEvict(value = ["fabricTotalCount"], allEntries = true)
        ]
    )
    fun evictAllCountsCache() {
    }
}

package com.lovelycat.shinkaibackend.controller;

import com.lovelycat.shinkaibackend.ShinkaiBackendApplication;
import com.lovelycat.shinkaibackend.response.Result;
import com.lovelycatv.arkcache.CacheTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @PostMapping("/invalidate-all-cache")
    public Result<?> invalidateAllCache() {
        ShinkaiBackendApplication.cacheTemplateContainer.getAllTemplates().forEach(CacheTemplate::removeAllCache);
        return Result.success("executed");
    }

}

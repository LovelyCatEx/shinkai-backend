package com.lovelycat.shinkaibackend.controller;

import com.lovelycat.shinkaibackend.entity.Creation;
import com.lovelycat.shinkaibackend.response.Result;
import com.lovelycat.shinkaibackend.service.CreationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creation")
public class CreationController {
    @Resource
    private CreationService creationService;

    @GetMapping("/all")
    public Result<?> getCreations() {
        return Result.success(creationService.getAllCreations());
    }

    @GetMapping("/details")
    public Result<?> getCreation(@RequestParam(name = "id") Long id) {
        Creation creation = creationService.getCreationById(id);
        if (creation == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "");
        } else {
            return Result.success(creation);
        }
    }
}

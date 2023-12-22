package com.lovelycat.shinkaibackend.controller;

import com.lovelycat.shinkaibackend.entity.Creation;
import com.lovelycat.shinkaibackend.entity.CreationCharacter;
import com.lovelycat.shinkaibackend.entity.CreationSection;
import com.lovelycat.shinkaibackend.response.Result;
import com.lovelycat.shinkaibackend.service.CreationCharacterService;
import com.lovelycat.shinkaibackend.service.CreationSectionService;
import com.lovelycat.shinkaibackend.service.CreationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/creation")
public class CreationController {
    @Resource
    private CreationService creationService;
    @Resource
    private CreationCharacterService creationCharacterService;
    @Resource
    private CreationSectionService creationSectionService;

    @GetMapping("/all")
    public Result<?> getCreations() {
        return Result.success(creationService.getAllCreations());
    }

    @GetMapping("/details")
    public Result<?> getCreation(@RequestParam(name = "id") Long id) {
        Creation creation = creationService.getCreationById(id);
        if (creation == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "Details not found");
        } else {
            return Result.success(creation);
        }
    }

    @GetMapping("/characters")
    public Result<?> getCreationCharacters(@RequestParam(name = "cid") Long creationId) {
        List<CreationCharacter> creationCharacters = creationCharacterService.getCreationCharacters(creationId);
        if (creationCharacters == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "Characters not found");
        } else {
            return Result.success(creationCharacters);
        }
    }

    @GetMapping("/sections")
    public Result<?> getCreationSections(@RequestParam(name = "cid") Long creationId) {
        List<CreationSection> creationCharacters = creationSectionService.getCreationSections(creationId);
        if (creationCharacters == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "Sections not found");
        } else {
            return Result.success(creationCharacters);
        }
    }
}

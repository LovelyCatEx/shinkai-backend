package com.lovelycat.shinkaibackend.controller;

import com.lovelycat.shinkaibackend.entity.GalleryImage;
import com.lovelycat.shinkaibackend.response.Result;
import com.lovelycat.shinkaibackend.service.GalleryImageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    @Resource
    private GalleryImageService galleryImageService;

    @GetMapping("/all")
    public Result<?> getAllImages() {
        List<GalleryImage> images = galleryImageService.getAllGalleryImages();
        if (images == null) {
            return Result.failed(Result.CODE_ERR_BAD_REQUEST, "");
        } else {
            return Result.success(images);
        }
    }
}

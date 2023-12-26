package com.lovelycat.shinkaibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lovelycat.shinkaibackend.entity.GalleryImage;

import java.util.List;

public interface GalleryImageService extends IService<GalleryImage> {
    List<GalleryImage> getAllGalleryImages();
}

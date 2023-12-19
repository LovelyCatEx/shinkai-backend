package com.lovelycat.shinkaibackend;

import com.lovelycatv.arkcache.related.CacheTemplateContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShinkaiBackendApplication {
    public static final CacheTemplateContainer cacheTemplateContainer = new CacheTemplateContainer();
    public static void main(String[] args) {
        SpringApplication.run(ShinkaiBackendApplication.class, args);
    }

}

package com.pwang.blog.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pwang.blog.repository")
@EntityScan(basePackages = "com.pwang.blog")
public class BlogApiApplication {
}

package com.pwang.blog.dto;

import lombok.Data;

@Data
public class BlogBestDto {

    private String keyword;
    private Long count;

    public BlogBestDto(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}

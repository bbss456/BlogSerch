package com.pwang.blog.dto;

import lombok.Data;

@Data
public class BlogBestDto {

    private int rank;
    private String keyword;
    private Long count;

    public BlogBestDto(int rank, String keyword, Long count) {
        this.rank = rank;
        this.keyword = keyword;
        this.count = count;
    }
}

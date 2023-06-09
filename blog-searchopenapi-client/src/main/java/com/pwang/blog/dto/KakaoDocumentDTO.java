package com.pwang.blog.dto;

import lombok.Data;

@Data
public class KakaoDocumentDTO {

    private String blogname;
    private String contents;
    private String thumbnail;
    private String title;
    private String url;
    String datetime;

    public void setNaver(NaverDocumentDTO naverDocumentDTO){
        this.blogname = naverDocumentDTO.getBloggername();
        this.thumbnail = "해당 블로그는 썸네일이 존재 하지 않습니다.";
        this.contents = naverDocumentDTO.getDescription();
        this.url = naverDocumentDTO.getLink();
        this.title = naverDocumentDTO.getTitle();
        this.datetime = naverDocumentDTO.getPostdate();
    }

}



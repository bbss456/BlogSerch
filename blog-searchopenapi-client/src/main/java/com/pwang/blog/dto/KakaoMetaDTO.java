package com.pwang.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoMetaDTO {

    @JsonProperty("id_end")
    private boolean  isEnd;

    @JsonProperty("total_count")
    private int totalCount;

    public void setNaver(boolean isend, int totalcount) {
        this.isEnd = isend;
        this.totalCount = totalcount;
    }

}

package com.pwang.blog.resposedto;

import com.pwang.blog.dto.BlogBestDto;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class BestKeywordResponseDTD {

    private List<BlogBestDto> blogBestDtoList = new ArrayList<>();

    public void add (BlogBestDto blogBestDto) {
        this.blogBestDtoList.add(blogBestDto);
    }

}

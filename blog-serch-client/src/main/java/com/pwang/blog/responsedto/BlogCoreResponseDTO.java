package com.pwang.blog.responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pwang.blog.dto.DocumentDTO;
import com.pwang.blog.dto.MetaDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlogCoreResponseDTO {

    @JsonProperty("documents")
    private List<DocumentDTO> documentDTOList = new ArrayList<>();

    @JsonProperty("meta")
    private MetaDTO metaDTO;


}

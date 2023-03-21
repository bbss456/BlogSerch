package com.pwang.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NaverResponseDTO {

    private int total;

    @JsonProperty("items")
    private List<NaverDocumentDTO> naverDocumentDTOList = new ArrayList<>();
}

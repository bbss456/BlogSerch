package com.pwang.blog.responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pwang.blog.dto.KakaoDocumentDTO;
import com.pwang.blog.dto.KakaoMetaDTO;
import com.pwang.blog.dto.NaverDocumentDTO;
import com.pwang.blog.dto.NaverResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class BlogCoreResponseDTO {

    @JsonProperty("documents")
    private List<KakaoDocumentDTO> kakaoDocumentDTOList = new ArrayList<>();

    @JsonProperty("meta")
    private KakaoMetaDTO kakaoMetaDTO;

    public void setNaverDTO(NaverResponseDTO naverResponseDTO,Boolean isend) {
        KakaoMetaDTO kakaoMetaDTOfromNaverDTO = new KakaoMetaDTO();
        kakaoMetaDTOfromNaverDTO.setNaver(isend, naverResponseDTO.getTotal());

        this.kakaoMetaDTO = kakaoMetaDTOfromNaverDTO;

        for (NaverDocumentDTO naverDocumentDTO : naverResponseDTO.getNaverDocumentDTOList()) {
            KakaoDocumentDTO kakaoDocumentDTOfromNaver = new KakaoDocumentDTO();
            kakaoDocumentDTOfromNaver.setNaver(naverDocumentDTO);

            this.kakaoDocumentDTOList.add(kakaoDocumentDTOfromNaver);
        }
    }
}

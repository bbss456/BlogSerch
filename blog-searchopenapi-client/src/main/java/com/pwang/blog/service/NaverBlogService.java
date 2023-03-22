package com.pwang.blog.service;

import com.pwang.blog.dto.NaverDocumentDTO;
import com.pwang.blog.dto.NaverResponseDTO;
import com.pwang.blog.exception.ErrorCode;
import com.pwang.blog.exception.ServiceException;
import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

@Service
public class NaverBlogService {
    @Value("${naver.blog.host}")
    private String naverUrlHost;

    @Value("${naver.blog.path}")
    private String naverUrlpath;

    @Value("${naver.X-Naver-Client-Id}")
    private String naverID;

    @Value("${naver.X-Naver-Client-Secret}")
    private String naverSecret;

    public BlogCoreResponseDTO getNaverBlogApi(String query, String sort, int page, int size) {

        UriComponents naverUrl = UriComponentsBuilder.newInstance()
                .scheme("https").host(naverUrlHost)
                .path(naverUrlpath)
                .queryParam("query", query)
                .queryParam("display", size)
                .queryParam("page", page)
                .queryParam("sort", this.getConvertKakaoSort(sort))
                .build();

        NaverResponseDTO naverResponseDTO= Optional.ofNullable(this.getNaverResposeDTO(naverUrl.toUriString()))
                .orElseThrow(()-> new ServiceException("Kakao And Naver Open Api service 500", ErrorCode.SERVICE_ERROR_VALUE));

        BlogCoreResponseDTO blogCoreResponseDTO = new BlogCoreResponseDTO();
        blogCoreResponseDTO.setNaverDTO(naverResponseDTO, this.getisEnd(page, size, naverResponseDTO.getTotal()));

        return blogCoreResponseDTO;
    }

    public NaverResponseDTO getNaverResposeDTO(String naverUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("X-Naver-Client-Id",naverID);
        headers.set("X-Naver-Client-Secret",naverSecret);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverResponseDTO> response;
        try {
            response = restTemplate.exchange(
                    naverUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<NaverResponseDTO>() {}
            );

        } catch (HttpClientErrorException error) {
            return null;
        }

        return response.getBody();
    }

    public String getConvertKakaoSort(String sort) {
        if(sort.equals("accuracy")){
            sort = "sim";
        } else {
            sort = "date";
        }

        return sort;
    }

    public Boolean getisEnd(int size, int page, int totalsize) {
        if (size*page > totalsize - size) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}

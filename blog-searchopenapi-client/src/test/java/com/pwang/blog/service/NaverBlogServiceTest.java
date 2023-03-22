package com.pwang.blog.service;

import com.pwang.blog.dto.NaverResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.*;

class NaverBlogServiceTest {

    @Test
    @DisplayName("네이버_OPEN_API")
    void getnaverOpenApi() {

        //given
        String naverUrlHost = "openapi.naver.com";
        String naverUrlpath = "/v1/search/blog";
        String naverID = "_YfKGq5fSmWp6oEv5_T9";
        String naverSecret = "98Ji9VgXio";
        String query = "카카오";
        int page = 1;
        String sort = "sim";
        int size = 10;

        UriComponents naverUrl = UriComponentsBuilder.newInstance()
                .scheme("https").host(naverUrlHost)
                .path(naverUrlpath)
                .queryParam("query", query)
                .queryParam("display", size)
                .queryParam("page", page)
                .queryParam("sort", sort)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("X-Naver-Client-Id",naverID);
        headers.set("X-Naver-Client-Secret",naverSecret);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverResponseDTO> response;

        //when
        response = restTemplate.exchange(
                naverUrl.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<NaverResponseDTO>() {}
        );

        //then
        assertEquals("200 OK", response.getStatusCode().toString());
    }
}
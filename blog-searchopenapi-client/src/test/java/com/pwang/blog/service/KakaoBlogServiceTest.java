package com.pwang.blog.service;

import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
class KakaoBlogServiceTest {

    NaverBlogService naverBlogService;
    KakaoBlogService kakaoBlogService;


    @BeforeEach
    public void beforeEach() {
        naverBlogService = new NaverBlogService();
        this.kakaoBlogService = new KakaoBlogService(naverBlogService);
        this.kakaoBlogService.setKakaoBlogService("10cb3a953131b9eecef358a3963f9774");
    }

    @Test
    public void 카카오OPENAPI_예외시_네이버블로그_API() {

        //given
        String query ="카카오";
        String sort = "accuracy";
        int page = 1;
        int size = 30;
        String kakaoUrlHost ="dapi.kakao.com";
        String kakaoUrlpath = "/v2/search/blog";

        UriComponents kakaoUrl = UriComponentsBuilder.newInstance()
                .scheme("https").host(kakaoUrlHost)
                .path(kakaoUrlpath)
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size",size)
                .build();

        //when
        BlogCoreResponseDTO blogCoreResponseDTO = Optional.ofNullable(kakaoBlogService.getKakaoBlogApi(kakaoUrl.toUriString()))
                .orElseGet(()-> naverBlogService.getNaverBlogApi(query, sort, page, size));
        System.out.println(blogCoreResponseDTO);

        Boolean result = Boolean.FALSE;
        if(blogCoreResponseDTO != null) {
            result = Boolean.TRUE;
        }

        //then
        assertEquals(true, result);
    }

    @Test
    public void 카카오_OPEN_API() {
        //given
        String query ="카카오";
        String sort = "accuracy";
        int page = 1;
        int size = 30;
        String kakaoUrlHost ="dapi.kakao.com";
        String kakaoUrlpath = "/v2/search/blog";
        String kakaoApiKey = "10cb3a953131b9eecef358a3963f9774";

        String url = UriComponentsBuilder.newInstance()
                .scheme("https").host(kakaoUrlHost)
                .path(kakaoUrlpath)
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size",size)
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BlogCoreResponseDTO> response;

        //when
        response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<BlogCoreResponseDTO>() {}
        );

        //then
        assertEquals("200 OK", response.getStatusCode().toString());
    }

}
package com.pwang.blog.service;

import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoBlogService {

    private final NaverBlogService naverBlogService;

//    @Value("${kakao.Apikey}")
    private String kakaoApiKey= "asd";


    public BlogCoreResponseDTO getBlogInfo(String url,String sort, int page, int size) {

        UriComponents kakaoUrl = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size",size)
                .build();

        BlogCoreResponseDTO blogCoreResponseDTO = Optional.ofNullable(getKakaoBlogApi(kakaoUrl.toUriString()))
                .orElseGet(()-> naverBlogService.getNaverBlogApi(url, sort, page, size));

        return blogCoreResponseDTO;
    }

    public BlogCoreResponseDTO getKakaoBlogApi(String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BlogCoreResponseDTO> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<BlogCoreResponseDTO>() {}
            );
            System.out.println(response.getBody());
        } catch (HttpClientErrorException error) {
            return null;
        }
        return response.getBody();
    }



}

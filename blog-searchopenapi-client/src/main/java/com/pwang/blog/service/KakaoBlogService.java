package com.pwang.blog.service;

import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class KakaoBlogService {

    private final NaverBlogService naverBlogService;

    @Value("${kakao.blog.host}")
    private String kakaoUrlHost;

    @Value("${kakao.blog.path}")
    private String kakaoUrlpath;

    @Value("${kakao.Apikey}")
    private String kakaoApiKey;

    public BlogCoreResponseDTO getBlogInfo(String query, String sort, int page, int size) {

        UriComponents kakaoUrl = UriComponentsBuilder.newInstance()
                .scheme("https").host(kakaoUrlHost)
                .path(kakaoUrlpath)
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size",size)
                .build();

        BlogCoreResponseDTO blogCoreResponseDTO = Optional.ofNullable(getKakaoBlogApi(kakaoUrl.toUriString()))
                .orElseGet(()-> naverBlogService.getNaverBlogApi(query, sort, page, size));

        return blogCoreResponseDTO;
    }

    public BlogCoreResponseDTO getKakaoBlogApi(String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
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
        } catch (HttpClientErrorException error) {
            log.error(error.toString());
            return null;
        }
        return response.getBody();
    }
}

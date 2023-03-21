package com.pwang.blog.service;

import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@Service
public class NaverBlogService {

    public BlogCoreResponseDTO getNaverBlogApi(String url, String sort, int page, int size) {

        System.out.println("2");
        String naverUrl = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size",size)
                .build().toUriString();
        System.out.println(naverUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
      //  headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BlogCoreResponseDTO> response = null;

        try {
            response = restTemplate.exchange(
                    naverUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<BlogCoreResponseDTO>(){}
            );
        } catch (HttpClientErrorException error) {
            return null;
        }

        return response.getBody();
    }

}

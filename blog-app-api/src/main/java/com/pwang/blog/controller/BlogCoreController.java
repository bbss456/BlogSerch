package com.pwang.blog.controller;


import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import com.pwang.blog.service.KakaoBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogCoreController {

//    @Value("${kakao.blog.host}")
    private String kakaoUrlHost = "asd";

//    @Value("${kakao.blog.path}")
    private String kakaoUrlpath = "fds";



    private final KakaoBlogService kakaoBlogService;

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return headers;
    }

    @GetMapping("/keyword")
    public ResponseEntity<BlogCoreResponseDTO> getBlogInfo(@RequestParam(required = true) String query,
                                                           @RequestParam(required = false, defaultValue = "accuracy") String sort,
                                                           @RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) throws ParseException {

        RestTemplate restTemplate = new RestTemplate();

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(kakaoUrlHost)
                .path(kakaoUrlpath)
                .queryParam("query", query)
                .build();

        return new ResponseEntity<>(kakaoBlogService.getBlogInfo(uriComponents.toUriString(), sort, page, size), this.headers(), HttpStatus.OK);
    }
}

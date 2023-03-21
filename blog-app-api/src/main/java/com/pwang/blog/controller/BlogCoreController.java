package com.pwang.blog.controller;

import com.pwang.blog.responsedto.BlogCoreResponseDTO;
import com.pwang.blog.resposedto.BestKeywordResponseDTD;
import com.pwang.blog.service.BlogSearchHistoryService;
import com.pwang.blog.service.KakaoBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogCoreController {

    private final KakaoBlogService kakaoBlogService;
    private final BlogSearchHistoryService blogSearchHistoryService;

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

        blogSearchHistoryService.saveOrupdate(query);

        return new ResponseEntity<>(kakaoBlogService.getBlogInfo(query, sort, page, size), this.headers(), HttpStatus.OK);
    }

    @GetMapping("/keyword/ten")
    public ResponseEntity<BestKeywordResponseDTD> getBestTenBlogKeyword() {

        return new ResponseEntity<>(blogSearchHistoryService.getBestTenkeyword(), this.headers(), HttpStatus.OK);
    }
}

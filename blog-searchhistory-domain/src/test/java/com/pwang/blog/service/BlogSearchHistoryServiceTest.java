package com.pwang.blog.service;

import com.pwang.blog.entity.BlogSearchHistory;
import com.pwang.blog.repository.BlogSearchHistoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BlogSearchHistoryServiceTest {

    @Autowired
    BlogSearchHistoryService blogSearchHistoryService;
    @Autowired
    BlogSearchHistoryRepository blogSearchHistoryRepository;

    @Test
    @DisplayName("인기 검색")
    void getBestTenkeyword() {
        //given

        //when
        List<BlogSearchHistory> blogSearchHistoryList = this.blogSearchHistoryRepository.findBestTenKeyword()
                .orElseThrow(()-> new RuntimeException("asd"));


        Boolean result = Boolean.FALSE;
        if (blogSearchHistoryList != null) {
            result = Boolean.TRUE;
        }

        //then
        assertEquals(true,result);
    }

    @Test
    @Transactional
    @DisplayName("테스트")
    void saveOrInit() {
        //given
        String  keyword = "keyword_one";

        //when
        Boolean result= this.blogSearchHistoryService.saveOrupdate(keyword);

        //then
        assertEquals(true, result);
    }

}
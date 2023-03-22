package com.pwang.blog.controller;

import com.pwang.blog.service.BlogSearchHistoryService;
import com.pwang.blog.service.KakaoBlogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BlogCoreController.class)
class BlogCoreControllerTest {

    @MockBean
    KakaoBlogService kakaoBlogService;
    @MockBean
    BlogSearchHistoryService blogSearchHistoryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("블로그 조회")
    public void getBlogInfo() throws Exception {
        mockMvc.perform(get("/blog/keyword?query=볶음밥&sort=accuracy&page=1&size=1"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("블로그 인기 검색어")
    public void getBest10() throws Exception {
        mockMvc.perform(get("/blog/keyword/ten"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
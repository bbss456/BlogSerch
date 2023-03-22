package com.pwang.blog.service;

import com.pwang.blog.dto.BlogBestDto;
import com.pwang.blog.entity.BlogSearchHistory;
import com.pwang.blog.repository.BlogSearchHistoryRepository;
import com.pwang.blog.resposedto.BestKeywordResponseDTD;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogSearchHistoryService {

    private final BlogSearchHistoryRepository blogSearchHistoryRepository;

    public BestKeywordResponseDTD getBestTenkeyword() {
        List<BlogSearchHistory> blogSearchHistoryList = blogSearchHistoryRepository.findBestTenKeyword()
                .orElseThrow(()-> new RuntimeException("asd"));

        BestKeywordResponseDTD bestKeywordResponseDTD = new BestKeywordResponseDTD();

        for (BlogSearchHistory blogSearchHistory  : blogSearchHistoryList) {
            BlogBestDto blogBestDto = new BlogBestDto(blogSearchHistory.getKeyword(), blogSearchHistory.getCount());
            bestKeywordResponseDTD.add(blogBestDto);
        }

        return bestKeywordResponseDTD;
    }

    @Synchronized
    public Boolean saveOrupdate(String keyword) {
        Optional<BlogSearchHistory> blogSearchHistoryOptional = blogSearchHistoryRepository.findByKeyword(keyword);

        if(blogSearchHistoryOptional.isPresent()) {
            BlogSearchHistory blogSearchHistory = blogSearchHistoryOptional.get();
            blogSearchHistory.increateCount();
            blogSearchHistoryRepository.saveAndFlush(blogSearchHistory);
        } else {
            this.saveInit(keyword);
        }

        return Boolean.TRUE;
    }

    public void saveInit(String keyword) {
        BlogSearchHistory blogSearchHistory = BlogSearchHistory
                .builder()
                .count(1L)
                .keyword(keyword)
                .build();
        blogSearchHistoryRepository.saveAndFlush(blogSearchHistory);
    }

}

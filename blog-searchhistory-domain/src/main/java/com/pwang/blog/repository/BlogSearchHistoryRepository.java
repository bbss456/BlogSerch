package com.pwang.blog.repository;

import com.pwang.blog.entity.BlogSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogSearchHistoryRepository extends JpaRepository<BlogSearchHistory, Long> {

    Optional<BlogSearchHistory> findByKeyword(String keyword);

    @Query("SELECT b FROM BlogSearchHistory b order by count desc limit 10")
    Optional<List<BlogSearchHistory>> findBestTenKeyword();
}

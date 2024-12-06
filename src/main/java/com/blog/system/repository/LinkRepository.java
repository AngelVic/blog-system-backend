package com.blog.system.repository;

import com.blog.system.model.Link;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByCategory(String category);
    
    @Query("SELECT l FROM Link l WHERE LOWER(l.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(l.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Link> searchByTitleOrDescription(@Param("keyword") String keyword);
    
    @Query("SELECT l FROM Link l ORDER BY l.createdAt DESC")
    List<Link> findMostRecentLinks(Pageable pageable);
    
    List<Link> findByCategoryIn(List<String> categories);
    
    List<Link> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT l.category, COUNT(l) FROM Link l GROUP BY l.category")
    List<Object[]> countLinksPerCategory();
}

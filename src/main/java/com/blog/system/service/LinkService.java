package com.blog.system.service;

import com.blog.system.model.Link;
import com.blog.system.model.dto.LinkDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface LinkService {
    Link createLink(LinkDTO linkDTO, String ipAddress);
    List<Link> getAllLinks();
    List<Link> getLinksByCategory(String category);
    List<Link> searchLinks(String keyword);
    
    // New methods
    List<Link> getMostRecentLinks(int pageSize);
    List<Link> getLinksByCategories(List<String> categories);
    List<Link> getLinksByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Long> getCategoryStatistics();
}

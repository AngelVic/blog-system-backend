package com.blog.system.service.impl;

import com.blog.system.model.Link;
import com.blog.system.model.dto.LinkDTO;
import com.blog.system.repository.LinkRepository;
import com.blog.system.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    
    private final LinkRepository linkRepository;

    @Override
    @Transactional
    public Link createLink(LinkDTO linkDTO, String ipAddress) {
        Link link = new Link();
        link.setTitle(linkDTO.getTitle());
        link.setDescription(linkDTO.getDescription());
        link.setUrl(linkDTO.getUrl());
        link.setCategory(linkDTO.getCategory());
        link.setIpAddress(ipAddress);
        return linkRepository.save(link);
    }

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public List<Link> getLinksByCategory(String category) {
        return linkRepository.findByCategory(category);
    }

    @Override
    public List<Link> searchLinks(String keyword) {
        return linkRepository.searchByTitleOrDescription(keyword);
    }
    
    @Override
    public List<Link> getMostRecentLinks(int pageSize) {
        return linkRepository.findMostRecentLinks(PageRequest.of(0, pageSize));
    }
    
    @Override
    public List<Link> getLinksByCategories(List<String> categories) {
        return linkRepository.findByCategoryIn(categories);
    }
    
    @Override
    public List<Link> getLinksByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return linkRepository.findByCreatedAtBetween(startDate, endDate);
    }
    
    @Override
    public Map<String, Long> getCategoryStatistics() {
        return linkRepository.countLinksPerCategory().stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1]
            ));
    }
}

package com.blog.system.controller;

import com.blog.system.model.Link;
import com.blog.system.model.dto.LinkDTO;
import com.blog.system.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<Link> createLink(@Valid @RequestBody LinkDTO linkDTO, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        Link createdLink = linkService.createLink(linkDTO, ipAddress);
        return ResponseEntity.ok(createdLink);
    }

    @GetMapping
    public ResponseEntity<List<Link>> getAllLinks() {
        return ResponseEntity.ok(linkService.getAllLinks());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Link>> getLinksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(linkService.getLinksByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Link>> searchLinks(@RequestParam String keyword) {
        return ResponseEntity.ok(linkService.searchLinks(keyword));
    }
}

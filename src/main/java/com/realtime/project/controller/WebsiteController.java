package com.realtime.project.controller;

import com.realtime.project.entity.Tags;
import com.realtime.project.entity.Website;
import com.realtime.project.entity.WebsiteInfo;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @GetMapping(value = "/{websiteName}")
    public Mono<Website> findWebsiteByName(@PathVariable String websiteName) throws DataDoesNotExistsException {
        Website website = websiteService.fetchWebsiteByName(websiteName);
        return Mono.just(website);
    }

    @GetMapping(value = "/id/{websiteId}")
    public Mono<Website> findWebsite(@PathVariable String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.fetchWebsiteById(websiteId);
        return Mono.just(website);
    }

    @PostMapping(value = "/add")
    public Mono<Website> submitWebsite(@RequestBody Website website) {
        Website submitWebsite = websiteService.submitWebsite(website);
        return Mono.just(submitWebsite);
    }

    @PutMapping(value = "/update/images")
    public Mono<Website> addWebsiteInfo(@RequestBody List<WebsiteInfo> websiteInfo, @RequestParam String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.addWebsiteInfo(websiteId, websiteInfo);
        return Mono.just(website);
    }

    @PutMapping(value = "/update/tags")
    public Mono<Website> addTags(@RequestBody List<Tags> tags, @RequestParam String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.addTags(websiteId, tags);
        return Mono.just(website);
    }
}

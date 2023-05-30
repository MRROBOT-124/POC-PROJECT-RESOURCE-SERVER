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

/**
 * WEBSITE CONTROLLER THAT PROVIDES END POINTS USED TO SUBMIT A
 * WEBSITE OR GET WEBSITE DETAILS
 */
@RestController
@RequestMapping(value = "/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    /**
     * FETCH WEBSITE BASED ON WEBSITE NAME
     * @param websiteName "WEBSITE NAME"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    @GetMapping(value = "/{websiteName}")
    public Mono<Website> findWebsiteByName(@PathVariable String websiteName) throws DataDoesNotExistsException {
        Website website = websiteService.fetchWebsiteByName(websiteName);
        return Mono.just(website);
    }

    /**
     * FETCH WEBSITE BASED OM CUSTOM GENERATED ID
     * @param websiteId "WEBSITE ID"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    @GetMapping(value = "/id/{websiteId}")
    public Mono<Website> findWebsite(@PathVariable String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.fetchWebsiteById(websiteId);
        return Mono.just(website);
    }

    /**
     * SUBMIT WEBSITE
     * @param website "WEBSITE"
     * @return "WEBSITE"
     */
    @PostMapping(value = "/add")
    public Mono<Website> submitWebsite(@RequestBody Website website) {
        Website submitWebsite = websiteService.submitWebsite(website);
        return Mono.just(submitWebsite);
    }

    /**
     * UPDATING WEBSITE WITH IMAGES AND OTHER DETAILS
     * @param websiteInfo "WEBSITE INFO"
     * @param websiteId "WEBSITE ID"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    @PutMapping(value = "/update/images")
    public Mono<Website> addWebsiteInfo(@RequestBody List<WebsiteInfo> websiteInfo, @RequestParam String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.addWebsiteInfo(websiteId, websiteInfo);
        return Mono.just(website);
    }

    /**
     * ASSIGN TAGS TO A WEBSITE
     * @param tags "TAGS"
     * @param websiteId "WEBSITE ID"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    @PutMapping(value = "/update/tags")
    public Mono<Website> addTags(@RequestBody List<Tags> tags, @RequestParam String websiteId) throws DataDoesNotExistsException {
        Website website = websiteService.addTags(websiteId, tags);
        return Mono.just(website);
    }
}

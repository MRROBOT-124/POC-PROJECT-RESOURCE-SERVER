package com.realtime.project.controller;

import com.realtime.project.entity.Tags;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * TAGS CONTROLLER THAT PROVIDES END POINTS USED TO CREATE A NEW
 * TAG OR GET TAG DETAILS
 */
@RestController
@RequestMapping(value = "/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    /**
     * CREATE A NEW TAG AND ASSIGN IT TO A WEBSITE
     * @param tags "tags POJO"
     * @return "TAGS"
     */
    @PostMapping(value = "/add")
    public Mono<Tags> createTags(@RequestBody Tags tags) {
        Tags tag = tagsService.createTag(tags);
        return Mono.just(tag);
    }

    /**
     * GET TAG BASED ON CUSTOM GENERATED ID
     * @param id "WEBSITE ID"
     * @return "TAGS"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    @GetMapping(value = "/{id}")
    public Mono<Tags> getTag(@PathVariable String id) throws DataDoesNotExistsException {
        Tags tags = tagsService.fetchTag(id);
        return Mono.just(tags);
    }

    /**
     * GET ALL TAGS
     * @return "LIST OF TAGS"
     */
    @GetMapping(value = "/")
    public Mono<List<Tags>> getAllTags() {
        List<Tags> tags = tagsService.fetchTags();
        return Mono.just(tags);
    }
}

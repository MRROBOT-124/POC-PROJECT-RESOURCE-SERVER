package com.realtime.project.controller;

import com.realtime.project.entity.Tags;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @PostMapping(value = "/add")
    public Mono<Tags> createTags(@RequestBody Tags tags) {
        Tags tag = tagsService.createTag(tags);
        return Mono.just(tag);
    }

    @GetMapping(value = "/{id}")
    public Mono<Tags> getTag(@PathVariable String id) throws DataDoesNotExistsException {
        Tags tags = tagsService.fetchTag(id);
        return Mono.just(tags);
    }

    @GetMapping(value = "/")
    public Mono<List<Tags>> getAllTags() {
        List<Tags> tags = tagsService.fetchTags();
        return Mono.just(tags);
    }
}

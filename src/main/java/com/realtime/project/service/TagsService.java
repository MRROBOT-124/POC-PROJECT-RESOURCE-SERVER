package com.realtime.project.service;

import com.realtime.project.constants.HelperConstants;
import com.realtime.project.entity.Tags;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.repository.TagsRepository;
import com.realtime.project.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * REGISTER TAGS STORED IN POSTGRESQL DATABASE
 * THIS CLASS CONTAINS METHODS THAT CAN PULL THE NECESSARY RECORDS
 * NEEDED.
 */
@Service
public class TagsService {

    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private WebsiteRepository websiteRepository;

    /**
     * CREATE A TAG
     * @param tags "TAGS"
     * @return "TAGS"
     */
    public Tags createTag(Tags tags) {
        return tagsRepository.save(tags);
    }

    /**
     * FETCH ALL TAGS
     * @return "LIST OF TAGS"
     */
    public List<Tags> fetchTags() {
        return tagsRepository.findAll();
    }

    /**
     * FETCH TAG BASED ON CUSTOM GENERATED ID
     * @param id "ID"
     * @return "TAGS"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    public Tags fetchTag(String id) throws DataDoesNotExistsException {
        Optional<Tags> optionalTags = tagsRepository.findById(id);
        if(optionalTags.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.TAG_DOES_NOT_EXISTS);
        }
        return optionalTags.get();
    }

}

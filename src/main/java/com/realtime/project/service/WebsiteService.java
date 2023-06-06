package com.realtime.project.service;

import com.realtime.project.constants.HelperConstants;
import com.realtime.project.entity.OutboxWebsiteEntity;
import com.realtime.project.entity.Tags;
import com.realtime.project.entity.Website;
import com.realtime.project.entity.WebsiteInfo;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.repository.OutboxEventRepository;
import com.realtime.project.repository.WebsiteInfoRepository;
import com.realtime.project.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SUBMIT WEBSITE AND STORED IN POSTGRESQL DATABASE
 * THIS CLASS CONTAINS METHODS THAT CAN PULL THE NECESSARY RECORDS
 * NEEDED.
 */
@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;
    @Autowired
    private WebsiteInfoRepository websiteInfoRepository;
    @Autowired
    private OutboxEventRepository outboxEventRepository;

    /**
     * SUBMIT WEBSITE
     * @param website "WEBSITE"
     * @return "WEBSITE"
     */
    public Website submitWebsite(Website website, String username) {

        Website websiteDetails = websiteRepository.save(website);
        OutboxWebsiteEntity outboxEntity = OutboxWebsiteEntity.builder().aggregateid(websiteDetails.getId() + "_" + websiteDetails.getWebsiteName())
                .type(HelperConstants.WEBSITE)
                .aggregatetype(HelperConstants.WEBSITE)
                .payload(OutboxWebsiteEntity.convertJson(websiteDetails, username)).build();
        outboxEventRepository.save(outboxEntity);
        return websiteDetails;
    }

    /**
     * ADD WEBSITE INFO TO THE WEBSITE
     * @param websiteId "WEBSITE ID"
     * @param websiteInfo "WEBSITE INFO"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    public Website addWebsiteInfo(String websiteId, List<WebsiteInfo> websiteInfo, String username) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        Website website = optionalWebsite.get();
        website.setImages(websiteInfo);
        OutboxWebsiteEntity outboxEntity = OutboxWebsiteEntity.builder().aggregateid(website.getId() + "_" + website.getWebsiteName())
                .type(HelperConstants.WEBSITE)
                .aggregatetype(HelperConstants.WEBSITE)
                .payload(OutboxWebsiteEntity.convertJson(website, username)).build();
        outboxEventRepository.save(outboxEntity);
        return websiteRepository.save(website);
    }

    /**
     * GET WEBSITE BY WEBSITE NAME
     * @param websiteName "WEBSITE NAME"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    public Website fetchWebsiteByName(String websiteName) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findByWebsiteName(websiteName);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        return optionalWebsite.get();
    }

    /**
     * GET WEBSITE BY WEBSITE ID
     * @param websiteId "WEBSITE ID"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    public Website fetchWebsiteById(String websiteId) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        return optionalWebsite.get();
    }

    /**
     * ASSIGN TAG TO THE WEBSITE
     * @param websiteId "WEBSITE ID"
     * @param tags "TAGS"
     * @return "WEBSITE"
     * @throws DataDoesNotExistsException "DATA DOES NOT EXISTS"
     */
    public Website addTags(String websiteId, List<Tags> tags, String username) throws  DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        Website website = optionalWebsite.get();
        website.setTags(tags);
        OutboxWebsiteEntity outboxEntity = OutboxWebsiteEntity.builder().aggregateid(website.getId() + "_" + website.getWebsiteName())
                .type(HelperConstants.WEBSITE)
                .aggregatetype(HelperConstants.WEBSITE)
                .payload(OutboxWebsiteEntity.convertJson(website, username)).build();
        outboxEventRepository.save(outboxEntity);
        return websiteRepository.save(website);
    }

    /**
     * GET ALL WEBSITE
     * @return "LIST OF WEBSITE"
     */
    public List<Website> getAll() {
        return websiteRepository.findAll();
    }

}

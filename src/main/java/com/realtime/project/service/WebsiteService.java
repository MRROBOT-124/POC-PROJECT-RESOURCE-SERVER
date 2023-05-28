package com.realtime.project.service;

import com.realtime.project.constants.HelperConstants;
import com.realtime.project.entity.Tags;
import com.realtime.project.entity.Website;
import com.realtime.project.entity.WebsiteInfo;
import com.realtime.project.exceptions.DataDoesNotExistsException;
import com.realtime.project.repository.WebsiteInfoRepository;
import com.realtime.project.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;
    @Autowired
    private WebsiteInfoRepository websiteInfoRepository;

    public Website submitWebsite(Website website) {
        return websiteRepository.save(website);
    }

    public Website addWebsiteInfo(String websiteId, List<WebsiteInfo> websiteInfo) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        Website website = optionalWebsite.get();
        website.setImages(websiteInfo);
        return websiteRepository.save(website);
    }

    public Website fetchWebsiteByName(String websiteName) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findByWebsiteName(websiteName);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        return optionalWebsite.get();
    }

    public Website fetchWebsiteById(String websiteId) throws DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        return optionalWebsite.get();
    }

    public Website addTags(String websiteId, List<Tags> tags) throws  DataDoesNotExistsException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if(optionalWebsite.isEmpty()) {
            throw new DataDoesNotExistsException(HelperConstants.WEBSITE_DOES_NOT_EXISTS);
        }
        Website website = optionalWebsite.get();
        website.setTags(tags);
        return websiteRepository.save(website);
    }

}

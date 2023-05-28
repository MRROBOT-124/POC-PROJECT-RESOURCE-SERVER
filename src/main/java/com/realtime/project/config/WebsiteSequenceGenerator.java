package com.realtime.project.config;

import com.realtime.project.entity.Website;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.time.Instant;

public class WebsiteSequenceGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        Website website = (Website) object;
        return website.getWebsiteName() + "_" + Instant.now();
    }
}

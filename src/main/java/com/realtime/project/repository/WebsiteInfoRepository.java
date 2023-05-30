package com.realtime.project.repository;

import com.realtime.project.entity.WebsiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * REPOSITORY CLASS THAT CONTAINS ALL THE CRUD
 * OPERATIONS FOR THE WEBSITE INFO
 */
@Repository
public interface WebsiteInfoRepository extends JpaRepository<WebsiteInfo, Integer> {
}

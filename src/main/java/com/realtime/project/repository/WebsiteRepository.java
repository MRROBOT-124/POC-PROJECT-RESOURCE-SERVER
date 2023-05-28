package com.realtime.project.repository;

import com.realtime.project.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, String> {

    Optional<Website> findByWebsiteName(String websiteName);
}

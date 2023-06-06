package com.realtime.project.repository;

import com.realtime.project.entity.OutboxWebsiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxWebsiteEntity, String> {
}

package com.realtime.project.repository;

import com.realtime.project.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * REPOSITORY CLASS THAT CONTAINS ALL THE CRUD
 * OPERATIONS FOR THE TAGS
 */
@Repository
public interface TagsRepository extends JpaRepository<Tags, String> {
}

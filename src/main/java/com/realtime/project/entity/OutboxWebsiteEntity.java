package com.realtime.project.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.realtime.project.constants.HelperConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxWebsiteEntity implements Serializable {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString() + "_" + LocalDateTime.now();
    private String aggregatetype;
    private String aggregateid;
    private String type;
    @Column(columnDefinition = "TEXT")
    private String payload;
    private String username;
    @Builder.Default
    private final Instant timestamp = Instant.now();

    public static String convertJson(Website website, String username) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode asJson = objectMapper.createObjectNode()
                .put("aggregatetype", HelperConstants.WEBSITE)
                .put("aggregateid", website.getId() + "_" + website.getWebsiteName())
                .put("type", HelperConstants.WEBSITE)
                .put("id", website.getId())
                .put("username", username)
                .put("websiteName", website.getWebsiteName())
                .put("creatorName", website.getCreatorName())
                .put("description", website.getDescription())
                .put("submittedAt", website.getSubmittedAt().toString());
        ArrayNode clientAuthenticationMethods = asJson.putArray("tags");
        website.getTags().forEach(tags -> {
            ObjectNode objectNode = objectMapper.createObjectNode()
                    .put("tagName", tags.getTagName())
                    .put("tagDescription", tags.getTagDescription())
                    .put("createdAt", tags.getCreatedAt().toString());
            clientAuthenticationMethods.add(objectNode);
        });
        ArrayNode authorizationGrantTypes = asJson.putArray("images");
        website.getImages().forEach(images -> {
            ObjectNode objectNode = objectMapper.createObjectNode()
                    .put("images", images.getImages())
                    .put("createdAt", images.getCreatedAt().toString());
            authorizationGrantTypes.add(objectNode);
        });

        return asJson.toPrettyString();
    }


}

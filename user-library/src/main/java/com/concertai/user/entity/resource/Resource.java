package com.concertai.user.entity.resource;

public interface Resource {
    String getName();

    String getCreatedBy();

    String getUpdatedBy();

    Long getCreatedAt();

    Long getUpdatedAt();
}

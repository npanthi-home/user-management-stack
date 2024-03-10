package com.concertai.user.entity.resource;

public class All implements Resource {
    @Override
    public String getName() {
        return "all";
    }

    @Override
    public String getCreatedBy() {
        return "system";
    }

    @Override
    public String getUpdatedBy() {
        return "system";
    }

    @Override
    public Long getCreatedAt() {
        return null;
    }

    @Override
    public Long getUpdatedAt() {
        return null;
    }
}

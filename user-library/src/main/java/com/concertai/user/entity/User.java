package com.concertai.user.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Long createdAt;
    private Long updatedAt;
}

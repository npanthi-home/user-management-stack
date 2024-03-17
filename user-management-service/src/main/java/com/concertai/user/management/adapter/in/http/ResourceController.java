package com.concertai.user.management.adapter.in.http;

import com.concertai.user.entity.token.Secured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @PostMapping
    @Secured(permissions = {"resource:create"})
    public String create(@RequestHeader("authorization") String authorization) {
        String response = "resource created.";
        log.info(response);
        return response;
    }

    @PutMapping("{resourceId}/approval")
    @Secured(permissions = {"resource:approve"})
    public String approve(@PathVariable("resourceId") String resourceId, @RequestHeader("authorization") String authorization) {
        String response = String.format("resource %s approved.", resourceId);
        log.info(response);
        return response;
    }
}

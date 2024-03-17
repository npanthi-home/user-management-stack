package com.concertai.user.management.adapter.in.http;

import com.concertai.user.management.usecase.in.GetTokenByUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final GetTokenByUsername getTokenByUsername;

    @Autowired
    public TokenController(GetTokenByUsername getTokenByUsername) {
        this.getTokenByUsername = getTokenByUsername;
    }

    @PostMapping("/username/{username}")
    public String createToken(@PathVariable("username") String username) {
        return getTokenByUsername.apply(username);
    }
}

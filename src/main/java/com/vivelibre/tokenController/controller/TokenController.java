package com.vivelibre.tokenController.controller;

import com.vivelibre.tokenController.business.TokenService;
import com.vivelibre.tokenController.dtos.TokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TokenController {

    private final TokenService service;

    public TokenController(TokenService service){
        this.service = service;
    }

    @GetMapping("/get-token")
    public Mono<TokenResponse> getToken(){
        return  service.getToken();
    }

}

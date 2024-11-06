package com.vivelibre.tokenController.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenResponse {

    @JsonProperty("auth-vivelibre-token")
    private String token;

    @JsonProperty("date")
    private Date date;

}

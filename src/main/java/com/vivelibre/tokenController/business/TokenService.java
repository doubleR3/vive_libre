package com.vivelibre.tokenController.business;
import com.vivelibre.tokenController.dtos.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {


    private final WebClient webClient;

    public TokenService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<TokenResponse> getToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "auth-vivelibre");
        requestBody.put("password", "password");

        return webClient.post()
                .uri("/token")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(responseMap -> {
                    // Convertimos el Map en un TokenResponse
                    String token = (String) responseMap.get("token");
                    Date date = new Date();  // Asignamos la fecha actual o la que necesites
                    return new TokenResponse(token, date);
                });
    }

}

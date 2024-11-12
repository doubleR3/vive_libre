package com.vivelibre.tokenController.business;

import ch.qos.logback.core.subst.Token;
import com.vivelibre.tokenController.dtos.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TokenServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;
    @Mock
    private WebClient webClient;

    private TokenService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        // Inicializar TokenService con el mock de WebClient.Builder
        //service = new TokenService(webClientBuilder);
    }

    @Test
    public void testGetToken(){
        Map<String,String> response = new HashMap<>();
        response.put("token","tokenDePrueba");
        response.put("date", String.valueOf(new Date()));

        service = new TokenService(webClientBuilder);
        // Mockeamos el WebClient
        WebClient.RequestBodyUriSpec uriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri(Mockito.anyString())).thenReturn(uriSpec);
        when(uriSpec.bodyValue(Mockito.any())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Map.class)).thenReturn(Mono.just(response));

        Mono<TokenResponse> tokenResponseMono = service.getToken();
        TokenResponse tokenResponse = tokenResponseMono.block();

        assertEquals("tokenDePrueba", tokenResponse.getToken());
        assertEquals(new Date().getDate(), tokenResponse.getDate().getDate());
    }
}

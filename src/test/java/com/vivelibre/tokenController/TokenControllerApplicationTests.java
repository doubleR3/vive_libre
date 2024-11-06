package com.vivelibre.tokenController;

import com.vivelibre.tokenController.business.TokenService;
import com.vivelibre.tokenController.controller.TokenController;
import com.vivelibre.tokenController.dtos.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class TokenControllerApplicationTests {

	@Mock
	private TokenService tokenService;

	@InjectMocks
	private TokenController tokenController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetToken() {

		String token = "mocked-token";
		Date mockDate = new Date();
		TokenResponse mockedResponse = new TokenResponse();
		mockedResponse.setToken(token);
		mockedResponse.setDate(mockDate);

		when(tokenService.getToken()).thenReturn(Mono.just(mockedResponse));

		Mono<TokenResponse> responseMono = tokenController.getToken();
		TokenResponse response = responseMono.block();

		assertEquals(token, response.getToken());
		assertEquals(mockDate, response.getDate());
	}

}

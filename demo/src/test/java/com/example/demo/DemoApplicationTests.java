package com.example.demo;

import com.example.demo.dto.LineRequest;
import com.example.demo.dto.LineResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void okResponse() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//JSON body
//		String requestBody = "{ \"line\": \"aaabbbccr\"}";
		LineRequest requestBody = new LineRequest();
		requestBody.setLine("aaabbbccr");

		//init HttpEntity with JSON body
		HttpEntity<LineRequest> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<LineResponse> response = restTemplate
				.exchange(
						"http://localhost:" + port + "/frequency",
						HttpMethod.POST,
						request,
						LineResponse.class
						);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		LineResponse expectedResponseBody = new LineResponse();
		Map<Character, Long> responseMap = new HashMap<>();
		responseMap.put('a', 3L);
		responseMap.put('r', 1L);
		responseMap.put('b', 3L);
		responseMap.put('c', 2L);
		expectedResponseBody.setFrequency(responseMap);

		assertEquals(expectedResponseBody, response.getBody(), "Response body is not as expected");

	}

	@Test
	public void methodNotAllowedResponse() {
		//JSON body
		String requestBody = "";

		//init HttpEntity with JSON body
		HttpEntity<String> request = new HttpEntity<>(requestBody);

		ResponseEntity<String> response = restTemplate
				.exchange(
						"http://localhost:" + port + "/frequency",
						HttpMethod.GET,
						request,
						String.class
				);

		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());

	}

	@Test
	public void unsupportedMediaTypeResponse() {
		//JSON body
		String requestBody = "";

		//init HttpEntity with JSON body
		HttpEntity<String> request = new HttpEntity<>(requestBody);

		ResponseEntity<String> response = restTemplate
				.exchange(
						"http://localhost:" + port + "/frequency",
						HttpMethod.POST,
						request,
						String.class
				);

		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());

	}

	@Test
	public void badRequestResponse() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//JSON body
		LineRequest requestBody = new LineRequest();
		requestBody.setLine("");

		//init HttpEntity with JSON body
		HttpEntity<LineRequest> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<LineResponse> response = restTemplate
				.exchange(
						"http://localhost:" + port + "/frequency",
						HttpMethod.POST,
						request,
						LineResponse.class
				);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

}

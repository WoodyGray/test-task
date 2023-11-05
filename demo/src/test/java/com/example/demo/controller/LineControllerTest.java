package com.example.demo.controller;

import com.example.demo.dto.LineRequest;
import com.example.demo.dto.LineResponse;
import com.example.demo.service.LineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
public class LineControllerTest {
    @InjectMocks
    private LineController lineController;

    @Mock
    private LineService lineService;

    @Test
    void getLineFrequency_validRequest_okResponse() {
        LineRequest lineRequest = new LineRequest();
        lineRequest.setLine("aaabbbccr");

        Map<Character, Long> responseMap = new HashMap<>();
        responseMap.put('a', 3L);
        responseMap.put('b', 3L);
        responseMap.put('c', 2L);
        responseMap.put('r', 1L);

        LineResponse expectedResponse = new LineResponse();
        expectedResponse.setFrequency(responseMap);

        ResponseEntity<LineResponse> response = ResponseEntity.ok(expectedResponse);

//        doReturn(response).when(lineService.getLineFrequency(any(LineRequest.class)));
        when(lineService.getLineFrequency(any(LineRequest.class))).thenReturn(response);

        Assertions.assertEquals(response, lineController.getLineFrequency(lineRequest));
    }
}

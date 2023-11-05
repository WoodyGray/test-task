package com.example.demo.service;

import com.example.demo.dto.LineRequest;
import com.example.demo.dto.LineResponse;
import com.example.demo.exception.AppError;
import com.example.demo.exception.EmptyLineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class LineServiceTest {
    @InjectMocks
    private LineService lineService;

    @Test
    void getLineFrequency_validLine_true(){
        LineRequest lineRequest = new LineRequest();
        lineRequest.setLine("aaacccrrd");

        ResponseEntity<LineResponse> frequencyEntity =
                (ResponseEntity<LineResponse>) lineService.getLineFrequency(lineRequest);
        LineResponse lineResponse = frequencyEntity.getBody();

        Map<Character, Long> frequencyMap = new HashMap<>();
        frequencyMap.put('a', 3L);
        frequencyMap.put('r', 2L);
        frequencyMap.put('d', 1L);
        frequencyMap.put('c', 3L);

        assert lineResponse != null;
        Assertions.assertEquals(frequencyMap, lineResponse.getFrequency());
    }

    @Test
    void getLineFrequency_emptyLine_badRequest(){
        LineRequest lineRequest = new LineRequest();
        lineRequest.setLine("");

        EmptyLineException thrown = Assertions.assertThrows(
                EmptyLineException.class,
                () -> lineService.getLineFrequency(lineRequest),
                "no Exception"
        );

        Assertions.assertTrue(thrown.getMessage().contains("line value is incorrect"));
    }
}

package com.example.demo.service;

import com.example.demo.dto.LineRequest;
import com.example.demo.dto.LineResponse;
import com.example.demo.exception.AppError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineService {

    public ResponseEntity<?> getLineFrequency(LineRequest request){
        try {
            String line = request.getLine();
            if (!line.isEmpty()) {
                Map<Character, Long> frequency =
                        line.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                LineResponse response = new LineResponse();
                response.setFrequency(frequency);
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.BAD_REQUEST.value(), "line value is incorrect"),
                        HttpStatus.BAD_REQUEST
                );
            }
        }catch (Exception e){
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "line value is incorrect"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}

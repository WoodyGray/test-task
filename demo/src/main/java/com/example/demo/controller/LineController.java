package com.example.demo.controller;

import com.example.demo.dto.LineRequest;
import com.example.demo.dto.LineResponse;
import com.example.demo.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LineController {

    private final LineService service;

    @PostMapping("/frequency")
    public ResponseEntity<LineResponse> getLineFrequency(@RequestBody LineRequest request){
        return service.getLineFrequency(request);
    }
}

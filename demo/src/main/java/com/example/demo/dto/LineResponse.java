package com.example.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class LineResponse {
    private Map<Character, Long> frequency;
}

package com.example.chatbot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter @NoArgsConstructor
public class ResponseDto {
    private String version;
    private HashMap<String, Object> template;

    public ResponseDto(List<HashMap<String, Object>> outputs) {
        this.template = new HashMap<>();
        this.version = "2.0";
        this.template.put("outputs", outputs);
    }
}

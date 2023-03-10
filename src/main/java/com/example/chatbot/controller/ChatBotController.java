package com.example.chatbot.controller;

import com.example.chatbot.domain.Food;
import com.example.chatbot.service.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping ("/v1")
    public HashMap<String,Object> call(@RequestBody Map<String ,Object> params){
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
        String utter = userRequest.get("utterance").toString().replace("\n", "");
        Food food = chatBotService.findRandomFood(utter);
        List<HashMap<String, Object>> outputs = new ArrayList<>();
        HashMap<String, Object> template = new HashMap<>();
        HashMap<String, Object> simpleText = new HashMap<>();
        HashMap<String, Object> text = new HashMap<>();

        String textSt = food.getFoodName() + "는(은) 어떠신가요? 다시 선택하려면 점메추 버튼 클릭";
        text.put("text", textSt);
        simpleText.put("simpleText", text);
        outputs.add(simpleText);

        template.put("outputs", outputs);

        result.put("version", "2.0");
        result.put("template", template);
        return result;
    }
}

package com.example.chatbot.controller;

import com.example.chatbot.dto.Button;
import com.example.chatbot.dto.ResponseDto;
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

    @PostMapping("/v1")
    public ResponseDto findRandom(@RequestBody Map<String, Object> params) {
        HashMap<String, Object> action = (HashMap<String, Object>) params.get("action");
        HashMap<String, Object> param = (HashMap<String, Object>) action.get("params");
        String category = param.get("category").toString();
        List<HashMap<String, Object>> outputs = new ArrayList<>();
        HashMap<String, Object> simpleText = new HashMap<>();
        HashMap<String, Object> text = new HashMap<>();
        String s = chatBotService.listAllFood(category);
        s += "다시 선택하려면 점메추 버튼 클릭";

        text.put("text",s);
        simpleText.put("simpleText", text);
        outputs.add(simpleText);

        return new ResponseDto(outputs);
    }

    @PostMapping("/v2")
    public ResponseDto findRandomV2(@RequestBody Map<String, Object> params) {
        HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
        String utter = userRequest.get("utterance").toString().replace("\n", "");
        String food = chatBotService.findRandomFood(utter);

        List<HashMap<String, Object>> outputs = new ArrayList<>();
        HashMap<String, Object> output = new HashMap<>();
        HashMap<String, Object> basicCard = new HashMap<>();
        List<Object> buttons = new ArrayList<>();
        Button button1 = new Button("message", "카테고리 다시 선택", "점메추");
        Button button2 = new Button("message", utter + "전체 메뉴", utter + "전체 메뉴");

        String textSt = food + "는(은) 어떠신가요? 다시 선택하려면 점메추 버튼 클릭";
        buttons.add(button1);
        buttons.add(button2);
        basicCard.put("description", textSt);
        basicCard.put("buttons", buttons);
        output.put("basicCard", basicCard);

        outputs.add(output);

        return new ResponseDto(outputs);
    }
}

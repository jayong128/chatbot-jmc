package com.example.chatbot.service;

import com.example.chatbot.domain.Category;
import com.example.chatbot.domain.Food;
import com.example.chatbot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChatBotService {
    private final CategoryRepository categoryRepository;


    public Food findRandomFood(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName).get();
        Random random = new Random();
        List<Food> foodList = category.getFoodList();
        int randNum = random.nextInt(foodList.size());
        return foodList.get(randNum);
    }
}

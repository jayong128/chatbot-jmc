package com.example.chatbot.service;

import com.example.chatbot.domain.Category;
import com.example.chatbot.domain.Food;
import com.example.chatbot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChatBotService {
    private final CategoryRepository categoryRepository;


    public String findRandomFood(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName).get();
        Random random = new Random();
        List<Food> foodList = category.getFoodList();
        int randNum = random.nextInt(foodList.size());
        return foodList.get(randNum).getFoodName();
    }

    public String listAllFood(String categoryName) {
        String res = "";
        Category category = categoryRepository.findByCategoryName(categoryName).get();
        for (Food food : category.getFoodList()) {
            res += food.getFoodName();
            res += "\n";
        }
        return res;
    }
}

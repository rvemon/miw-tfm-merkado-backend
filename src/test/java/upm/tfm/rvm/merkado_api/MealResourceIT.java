package upm.tfm.rvm.merkado_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;
import upm.tfm.rvm.merkado_api.service.MealService;
import upm.tfm.rvm.merkado_api.rest.dtos.Meal;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MealResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MealService mealRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        //mealRepository.deleteAll();
    }

    @Test
    void testCreate() throws Exception {
        MealEntity meal = new MealEntity();
        meal.setUserId("user111");
        meal.setName("Breakfast");
        meal.setCategory("Morning");

        mockMvc.perform(post("/meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Breakfast"))
                .andExpect(jsonPath("$.category").value("Morning"));
    }

    @Test
    void testFindAllByUserId() throws Exception {
        MealEntity meal1 = new MealEntity("user1", "Breakfast", "Morning", LocalDate.now(), null, null);
        MealEntity meal2 = new MealEntity("user1", "Lunch", "Afternoon", LocalDate.now(), null, null);
        mealRepository.save(meal1);
        mealRepository.save(meal2);

        mockMvc.perform(get("/meals/userid/user1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Breakfast"))
                .andExpect(jsonPath("$[1].name").value("Lunch"));
    }

    @Test
    void getMealById() throws Exception {
        MealEntity meal = new MealEntity("user1", "Breakfast", "Morning", LocalDate.now(), null, null);
        meal = mealRepository.save(meal);

        mockMvc.perform(get("/meals/{id}", meal.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Breakfast"));
    }

    @Test
    void testUpdate() throws Exception {
        MealEntity meal = new MealEntity("user1", "Breakfast", "Morning", LocalDate.now(), null, null);
        meal = mealRepository.save(meal);

        Meal updatedMeal = new Meal();
        updatedMeal.setName("Updated Breakfast");
        updatedMeal.setCategory("Updated Morning");

        mockMvc.perform(put("/meals/{id}", meal.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMeal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Breakfast"))
                .andExpect(jsonPath("$.category").value("Updated Morning"));
    }

    @Test
    void testDelete() throws Exception {
        MealEntity meal = new MealEntity("user1", "Breakfast", "Morning", LocalDate.now(), null, null);
        meal = mealRepository.save(meal);

        mockMvc.perform(delete("/meals/{id}", meal.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assert(mealRepository.getOne(meal.getId())!=null);
    }


}

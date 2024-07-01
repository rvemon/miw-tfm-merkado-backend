package upm.tfm.rvm.merkado_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.service.IngredientService;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IngredientResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientService ingredientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        ingredientRepository.deleteAll();
    }

    @Test
    void testCreate() throws Exception {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setName("Tomato");

        mockMvc.perform(post("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ingredient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tomato"));
    }

    @Test
    void testFindAllByUserId() throws Exception {
        IngredientEntity ingredient1 = new IngredientEntity();
        ingredient1.setId(UUID.randomUUID().toString());
        ingredient1.setName("Tomato");
        ingredient1.setUserId("2");
        ingredientRepository.save(ingredient1);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(UUID.randomUUID().toString());
        ingredient2.setUserId("2");
        ingredient2.setName("Cucumber");
        ingredientRepository.save(ingredient2);

        mockMvc.perform(get("/ingredients/userid/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tomato"))
                .andExpect(jsonPath("$[1].name").value("Cucumber"));
    }

    @Test
    void getIngredientById() throws Exception {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setName("Tomato");
        ingredient.setId(UUID.randomUUID().toString());
        ingredient = ingredientRepository.save(ingredient);

        mockMvc.perform(get("/ingredients/{id}", ingredient.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tomato"));
    }

    @Test
    void testUpdate() throws Exception {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setName("Tomato");
        ingredient.setId(UUID.randomUUID().toString());
        ingredient = ingredientRepository.save(ingredient);

        ingredient.setName("Updated Tomato");

        mockMvc.perform(put("/ingredients/{id}", ingredient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ingredient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Tomato"));
    }

    @Test
    void testDelete() throws Exception {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setName("Tomato");
        ingredient.setId(UUID.randomUUID().toString());
        ingredient = ingredientRepository.save(ingredient);

        mockMvc.perform(delete("/ingredients/{id}", ingredient.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        IngredientEntity deletedIngredient = ingredientRepository.getOne(ingredient.getId());
        assert(deletedIngredient != null);
    }
}

package upm.tfm.rvm.merkado_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.DailyMenu;
import upm.tfm.rvm.merkado_api.service.DailyMenuService;
import upm.tfm.rvm.merkado_api.service.MealService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DailyMenuResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DailyMenuService dailyMenuService;

    @MockBean
    private MealService mealService;

    private DailyMenuEntity dailyMenuEntity;
    private DailyMenu dailyMenu;
    private MealEntity mealEntity;

    @BeforeEach
    void setUp() {
        mealEntity = new MealEntity();
        mealEntity.setId("meal1");
        mealEntity.setName("Breakfast");
        mealEntity.setCategory("Morning");

        List<MealEntity> meals = new ArrayList<>();
        meals.add(mealEntity);

        dailyMenuEntity = new DailyMenuEntity();
        dailyMenuEntity.setId("dailyMenu1");
        dailyMenuEntity.setUserId("user1");
        dailyMenuEntity.setName("Daily Menu 1");
        dailyMenuEntity.setMealEntities(meals);
        dailyMenuEntity.setCreationDate(LocalDate.now());
        dailyMenuEntity.setDay("MONDAY");

        dailyMenu = new DailyMenu(dailyMenuEntity);

        Mockito.when(mealService.findAll()).thenReturn(meals);
        Mockito.when(dailyMenuService.save(any(DailyMenuEntity.class))).thenReturn(dailyMenuEntity);
        Mockito.when(dailyMenuService.getOne(anyString())).thenReturn(dailyMenuEntity);
        Mockito.when(dailyMenuService.findAllByUserId(anyString())).thenReturn(Stream.of(dailyMenuEntity));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/daily-menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dailyMenu)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Daily Menu 1"));
    }

    @Test
    void testFindAllByUserId() throws Exception {
        mockMvc.perform(get("/daily-menus/userid/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Daily Menu 1"));
    }

    @Test
    void testGetDailyMenuById() throws Exception {
        mockMvc.perform(get("/daily-menus/dailyMenu1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Daily Menu 1"));
    }

    @Test
    void testUpdate() throws Exception {
        dailyMenu.setName("Updated Daily Menu");

        mockMvc.perform(put("/daily-menus/dailyMenu1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dailyMenu)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Daily Menu"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/daily-menus/dailyMenu1"))
                .andExpect(status().isOk());
    }
}

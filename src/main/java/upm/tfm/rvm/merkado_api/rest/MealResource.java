package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientRepository;
import upm.tfm.rvm.merkado_api.rest.dtos.Ingredient;
import upm.tfm.rvm.merkado_api.rest.dtos.Meal;

import upm.tfm.rvm.merkado_api.rest.dtos.MealIngredient;
import upm.tfm.rvm.merkado_api.service.IngredientService;
import upm.tfm.rvm.merkado_api.service.MealService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(MealResource.MEAL)
public class MealResource {
    static final String MEAL = "/meals";
    static final String MEAL_BY_USERID = "/userid/{id}";
    static final String ID_ID = "/{id}";

    private MealService mealService;

    @Autowired
    public MealResource(MealService mealService){
        this.mealService = mealService;
    }

    @GetMapping(MEAL_BY_USERID)
    public List<Meal> findAllByUserId(
            @PathVariable String id
    ){
        Stream<MealEntity> pe= this.mealService.findAllByUserId(id);
        return pe.map(Meal::new).collect(Collectors.toList());

    }

    private List<MealIngredientEntity> getIngredientList(Meal meal) {
        if (meal.getIngredients() != null) {
            return meal.getIngredients().stream().map(mi -> {
                MealIngredientEntity mealIngredientEntity = new MealIngredientEntity();
                mealIngredientEntity.setIngredient(new IngredientEntity());
                mealIngredientEntity.getIngredient().setId(mi.getIngredient().getId());
                mealIngredientEntity.setQuantity(mi.getQuantity());
                return mealIngredientEntity;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal){
        MealEntity mealEntity = new MealEntity(
                meal.getUserId(),
                meal.getName(),
                meal.getCategory(),
                LocalDate.now(),
                null,
                getIngredientList(meal)
        );
        return new Meal(this.mealService.save(mealEntity));
    }

    @GetMapping(ID_ID)
    public  Meal getDailyMenuById(@PathVariable  String id){
        MealEntity mealEntity= this.mealService.getOne(id);
        if(mealEntity!=null){
            return new Meal(mealEntity);
        }
        return null;
    }

    @PutMapping(ID_ID)
    public Meal update(
            @PathVariable String id,
            @RequestBody Meal meal
    ){
        MealEntity mealEntity = this.mealService.getOne(id);
        if (mealEntity != null) {
            mealEntity.setName(meal.getName());
            mealEntity.setCategory(meal.getCategory());
            List<MealIngredientEntity> mealIngredients = getIngredientList(meal);
            assert mealIngredients != null;
            for (MealIngredientEntity mi : mealIngredients) {
                mi.setMeal(mealEntity); // Asegura que la relación se establece correctamente
            }
            mealEntity.setMealIngredients(mealIngredients);
        }

        this.mealService.save(mealEntity);
        return new Meal(mealEntity);
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id){
        this.mealService.delete(id);
    }
}

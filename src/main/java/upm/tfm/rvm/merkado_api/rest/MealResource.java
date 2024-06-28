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
    private IngredientService ingredientService;

    @Autowired
    public MealResource(MealService mealService, IngredientService ingredientService){
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(MEAL_BY_USERID)
    public List<Meal> findAllByUserId(
            @PathVariable String id
    ){
        Stream<MealEntity> pe= this.mealService.findAllByUserId(id);
        return pe.map(Meal::new).collect(Collectors.toList());

    }



    @PostMapping
    public Meal create(@RequestBody Meal meal){
        MealEntity mealEntity = new MealEntity(
                meal.getUserId(),
                meal.getName(),
                meal.getCategory(),
                LocalDate.now(),
                null,
                null
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

    private List<MealIngredientEntity> getIngredientList(Meal meal, MealEntity mealEntity) {
        if (meal.getIngredients() != null) {
            return meal.getIngredients()
                    .stream()
                    .map(ingredientDto -> {
                        MealIngredientEntity mealIngredientEntity = new MealIngredientEntity();
                        mealIngredientEntity.setMeal(mealEntity);

                        mealIngredientEntity.setId(ingredientDto.getId());

                        IngredientEntity ingredientEntity = new IngredientEntity();
                        ingredientEntity.setId(ingredientDto.getIngredient().getId());
                        mealIngredientEntity.setIngredient(ingredientEntity);

                        mealIngredientEntity.setQuantity(ingredientDto.getQuantity());
                        return mealIngredientEntity;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }


    @PutMapping(ID_ID)
    public Meal update(
            @PathVariable String id,
            @RequestBody Meal meal
    ) {
        MealEntity mealEntity = this.mealService.getOne(id);
        if (mealEntity != null) {
            mealEntity.setName(meal.getName());
            mealEntity.setCategory(meal.getCategory());

            // Obtener la lista de ingredientes
            List<MealIngredientEntity> ingredients = getIngredientList(meal, mealEntity);
            if (ingredients != null) {
                for (MealIngredientEntity mealIngredient : ingredients) {
                    this.mealService.saveMealIngredient(mealIngredient);
                }
            }

            mealEntity.setMealIngredients(ingredients);

            this.mealService.save(mealEntity);
        }
        return new Meal(mealEntity);
    }


    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id){
        this.mealService.delete(id);
    }
}

package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.Ingredient;
import upm.tfm.rvm.merkado_api.rest.dtos.Meal;
import upm.tfm.rvm.merkado_api.service.IngredientService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(IngredientResource.INGREDIENT)
public class IngredientResource {
    static final String INGREDIENT = "/ingredients";
    static final String INGREDIENT_BY_USERID = "/userid/{id}";
    static final String ID_ID = "/{id}";

    private IngredientService ingredientService;

    @Autowired
    public IngredientResource(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @GetMapping(INGREDIENT_BY_USERID)
    public List<Ingredient> findAllByUserId(
            @PathVariable String id
        ){
        Stream<IngredientEntity> pe= this.ingredientService.findAllByUserId(id);
        return pe.map(Ingredient::new).collect(Collectors.toList());

    }


    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient){
        IngredientEntity ingredientEntity = new IngredientEntity(
                ingredient.getUserId(),
                ingredient.getName(),
                ingredient.getIngredientType(),
                ingredient.getMeasurement(),
                LocalDate.now(),
                null
        );
        return new Ingredient(this.ingredientService.save(ingredientEntity));
    }

    @PutMapping(ID_ID)
    public Ingredient update(@PathVariable String id,
                             @RequestBody Ingredient ingredient){
        IngredientEntity ingredientEntity = this.ingredientService.getOne(id);
        if(ingredientEntity!=null){
            ingredientEntity.setName(ingredient.getName());
            ingredientEntity.setIngredientType(ingredient.getIngredientType());
            ingredientEntity.setMeasurement(ingredient.getMeasurement());
        }

        this.ingredientService.save(ingredientEntity);
        return new Ingredient(ingredientEntity);
    }

    @GetMapping(ID_ID)
    public Ingredient getIngredientById(@PathVariable  String id){
        IngredientEntity ingredientEntity= this.ingredientService.getOne(id);
        if(ingredientEntity!=null){
            return new Ingredient(ingredientEntity);
        }
        return null;
    }


    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id){
        this.ingredientService.delete(id);
    }

}

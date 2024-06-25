package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.Ingredient;
import upm.tfm.rvm.merkado_api.service.IngredientService;

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
    public Ingredient create(Ingredient ingredient){
        IngredientEntity ingredientEntity = new IngredientEntity(
                ingredient.getUserId(),
                ingredient.getName(),
                ingredient.getIngredientType(),
                ingredient.getMeasurement(),
                ingredient.getCreationDate(),
                null
        );
        return new Ingredient(this.ingredientService.save(ingredientEntity));
    }

    @GetMapping(ID_ID)
    public Ingredient getDailyMenuById(@PathVariable  String id){
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

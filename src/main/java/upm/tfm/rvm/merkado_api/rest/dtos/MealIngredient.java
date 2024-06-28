package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;

@Getter
public class MealIngredient {

    private Long id;

    @JsonIgnore
    private Meal meal;
    private Ingredient ingredient;
    private Integer quantity;

    public MealIngredient(){

    }

    public MealIngredient(MealIngredientEntity mealIngredientEntity){
        this.id = mealIngredientEntity.getId();
        MealEntity me = new MealEntity();
        me.setId(mealIngredientEntity.getMeal().getId());
        this.meal = new Meal(me);
        this.ingredient = new Ingredient(mealIngredientEntity.getIngredient());
        this.quantity = mealIngredientEntity.getQuantity();
    }

    public MealIngredientEntity toEntity(){
        MealIngredientEntity mealIngredient = new MealIngredientEntity();

        MealEntity mealEntity = new MealEntity();
        BeanUtils.copyProperties( this.meal, mealEntity, "ingredients", "menusIn");

        IngredientEntity ingredientEntity = new IngredientEntity();
        BeanUtils.copyProperties( this.ingredient, ingredientEntity, "mealsIn");

        mealIngredient.setId(this.id);
        mealIngredient.setMeal(mealEntity);
        mealIngredient.setIngredient(ingredientEntity);
        mealIngredient.setQuantity(this.quantity);

        return mealIngredient;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "ingredient='" + ingredient + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

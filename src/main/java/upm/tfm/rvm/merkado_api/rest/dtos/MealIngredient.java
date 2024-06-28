package upm.tfm.rvm.merkado_api.rest.dtos;

import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;

public class MealIngredient {
    private Meal meal;
    private Ingredient ingredient;
    private Integer quantity;

    public MealIngredient(){

    }

    public MealIngredient(MealIngredientEntity mealIngredientEntity){
        BeanUtils.copyProperties(mealIngredientEntity, this);
    }

    public MealIngredientEntity toEntity(){
        MealIngredientEntity mealIngredient = new MealIngredientEntity();

        MealEntity mealEntity = new MealEntity();
        BeanUtils.copyProperties( this.meal, mealEntity, "ingredients", "menusIn");

        IngredientEntity ingredientEntity = new IngredientEntity();
        BeanUtils.copyProperties( this.ingredient, ingredientEntity, "mealsIn");

        mealIngredient.setMeal(mealEntity);
        mealIngredient.setIngredient(ingredientEntity);
        mealIngredient.setQuantity(this.quantity);

        return mealIngredient;
    }


    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
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

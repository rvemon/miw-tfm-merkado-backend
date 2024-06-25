package upm.tfm.rvm.merkado_api.rest.dtos;

import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;

public class MealIngredient {
    private String mealId;
    private String ingredientId;
    private Integer quantity;

    public MealIngredient(){

    }

    public MealIngredient(MealIngredientEntity mealIngredientEntity){
        BeanUtils.copyProperties(mealIngredientEntity, this);
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "mealId='" + mealId + '\'' +
                ", ingredientId='" + ingredientId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

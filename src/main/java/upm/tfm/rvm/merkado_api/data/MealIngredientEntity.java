package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name="meal_ingredients")
public class MealIngredientEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    private Integer quantity;

    public MealIngredientEntity(){
    }


    public MealIngredientEntity(MealEntity meal, IngredientEntity ingredient, Integer quantity) {
        this.id = UUID.randomUUID().toString();
        this.meal = meal;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}





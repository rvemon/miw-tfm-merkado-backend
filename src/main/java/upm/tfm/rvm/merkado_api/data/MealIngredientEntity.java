package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name="meal_ingredient")
public class MealIngredientEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    private Integer quantity;

    public MealIngredientEntity(){
    }


    public MealIngredientEntity(Long id, MealEntity meal, IngredientEntity ingredient, Integer quantity) {
        this.id = id;
        this.meal = meal;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public void setId(Long id) {
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





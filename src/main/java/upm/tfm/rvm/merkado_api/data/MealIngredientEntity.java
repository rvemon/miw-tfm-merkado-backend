package upm.tfm.rvm.merkado_api.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name="meal_ingredients")
public class MealIngredientEntity {

    @EmbeddedId
    private MealIngredientId id;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    private Integer quantity;

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

@Getter
@Embeddable
class MealIngredientId implements Serializable {

    @Column(name = "meal_id")
    private String mealId;

    @Column(name = "ingredient_id")
    private String ingredientId;

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }
}



package upm.tfm.rvm.merkado_api.data;

import javax.persistence.*;
import java.io.Serializable;

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

    private int quantity;

    public MealIngredientId getId() {
        return id;
    }

    public void setId(MealIngredientId id) {
        this.id = id;
    }

    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

@Embeddable
class MealIngredientId implements Serializable {

    @Column(name = "meal_id")
    private String mealId;

    @Column(name = "ingredient_id")
    private String ingredientId;

    // Getters, setters, equals, and hashCode
}

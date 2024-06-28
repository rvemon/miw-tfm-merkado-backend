package upm.tfm.rvm.merkado_api.rest.dtos;

import lombok.Getter;

@Getter
public class ShoppingItem {
    Ingredient ingredient;
    Integer quantity;

    public ShoppingItem(Ingredient ingredient, Integer quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
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
                "ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}

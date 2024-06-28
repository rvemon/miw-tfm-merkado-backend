package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.MealEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Meal {
    private String id;
    private String userId;
    private String name;
    private String category;
    @JsonIgnore
    private List<DailyMenu> menusIn;

    private List<MealIngredient> ingredients;

    public Meal(){

    }

    public Meal(MealEntity mealEntity){
        BeanUtils.copyProperties(mealEntity, this,
                "menusIn", "ingredients");
        if(mealEntity.getMealIngredients()!=null){
            this.setIngredients(mealEntity.getMealIngredients()
                    .stream().map(MealIngredient::new)
                    .collect(Collectors.toList()));
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMenusIn(List<DailyMenu> menusIn) {
        this.menusIn = menusIn;
    }

    public void setIngredients(List<MealIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}

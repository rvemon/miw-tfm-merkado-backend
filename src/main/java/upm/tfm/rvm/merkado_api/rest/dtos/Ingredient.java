package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;

import java.time.LocalDate;
import java.util.List;

public class Ingredient {
    private String id;

    private String userId;
    private String name;
    private String ingredientType;
    private String measurement;
    private LocalDate creationDate;
    @JsonIgnore
    private List<MealIngredientEntity> mealsIn;

    public Ingredient(){

    }

    public Ingredient(IngredientEntity ingredientEntity){
        BeanUtils.copyProperties(ingredientEntity, this,"mealsIn");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<MealIngredientEntity> getMealsIn() {
        return mealsIn;
    }

    public void setMealsIn(List<MealIngredientEntity> mealsIn) {
        this.mealsIn = mealsIn;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredientType='" + ingredientType + '\'' +
                ", measurement='" + measurement + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}

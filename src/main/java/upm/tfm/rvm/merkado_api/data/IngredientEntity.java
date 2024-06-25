package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class IngredientEntity {

    @Id
    private String id;
    private String name;
    private String ingredientType;
    private String measurement;
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "mealIngredients"
    )
    private List<MealEntity> mealEntities;

    public IngredientEntity(){

    }

    public IngredientEntity(String name, String ingredientType, String measurement,
                            LocalDate creationDate, List<MealEntity> mealEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.ingredientType = ingredientType;
        this.measurement = measurement;
        this.creationDate = creationDate;
        this.mealEntities = mealEntities;
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

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<MealEntity> getMealEntities() {
        return mealEntities;
    }

    public void setMealEntities(List<MealEntity> mealEntities) {
        this.mealEntities = mealEntities;
    }
}

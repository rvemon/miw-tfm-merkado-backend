package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MealEntity {

    @Id
    private String id;
    private String userId;
    private String name;
    private String category;
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "mealEntities"
    )
    private List<DailyMenuEntity> dailyMenuEntities;

    @ManyToMany
    @JoinTable(
            name="meal_ingredients",
            joinColumns = @JoinColumn(name="meal_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<IngredientEntity> mealIngredients;


    public MealEntity(){

    }

    public MealEntity(String userId, String name, String category, LocalDate creationDate,
                      List<DailyMenuEntity> dailyMenuEntities, List<IngredientEntity> mealIngredients) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.category = category;
        this.creationDate = creationDate;
        this.dailyMenuEntities = dailyMenuEntities;
        this.mealIngredients = mealIngredients;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<DailyMenuEntity> getDailyMenuEntities() {
        return dailyMenuEntities;
    }

    public void setDailyMenuEntities(List<DailyMenuEntity> dailyMenuEntities) {
        this.dailyMenuEntities = dailyMenuEntities;
    }

    public List<IngredientEntity> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<IngredientEntity> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }
}

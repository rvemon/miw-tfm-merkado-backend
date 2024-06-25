package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name="meal")
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

    @OneToMany(
            mappedBy = "meal"
    )
    private List<MealIngredientEntity> mealIngredients;


    public MealEntity(){
    }

    public MealEntity(String userId, String name, String category, LocalDate creationDate,
                      List<DailyMenuEntity> dailyMenuEntities, List<MealIngredientEntity> mealIngredients) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.category = category;
        this.creationDate = creationDate;
        this.dailyMenuEntities = dailyMenuEntities;
        this.mealIngredients = mealIngredients;
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

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setDailyMenuEntities(List<DailyMenuEntity> dailyMenuEntities) {
        this.dailyMenuEntities = dailyMenuEntities;
    }

    public void setMealIngredients(List<MealIngredientEntity> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }
}

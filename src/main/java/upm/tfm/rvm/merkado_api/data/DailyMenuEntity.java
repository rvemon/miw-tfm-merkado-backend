package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name="daily_menu")
public class DailyMenuEntity {

    @Id
    private String id;
    private String userId;
    private String name;
    private String day;
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "dailyMenuEntities"
    )
    private List<PlannerEntity> plannerEntities;

    @ManyToMany
    @JoinTable(
            name="daily_menu_meals",
            joinColumns = @JoinColumn(name="daily_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private List<MealEntity> mealEntities;

    public DailyMenuEntity(){

    }
    public DailyMenuEntity(String userId, String name, List<PlannerEntity> plannerEntities,
                           List<MealEntity> mealEntities, LocalDate creationDate, String day) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.plannerEntities = plannerEntities;
        this.mealEntities = mealEntities;
        this.creationDate = creationDate;
        this.day = day;
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

    public void setPlannerEntities(List<PlannerEntity> plannerEntities) {
        this.plannerEntities = plannerEntities;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setMealEntities(List<MealEntity> mealEntities) {
        this.mealEntities = mealEntities;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

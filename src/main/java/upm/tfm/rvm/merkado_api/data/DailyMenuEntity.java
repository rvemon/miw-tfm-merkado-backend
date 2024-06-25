package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public List<PlannerEntity> getPlannerEntities() {
        return plannerEntities;
    }

    public void setPlannerEntities(List<PlannerEntity> plannerEntities) {
        this.plannerEntities = plannerEntities;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

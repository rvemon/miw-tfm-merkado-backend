package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DailyMenu {
    private String id;
    private String name;
    private String userId;
    private String day;

    private LocalDate creationDate;

    @JsonIgnore
    private List<Planner> plannersIn;

    private List<Meal> meals;

    public DailyMenu(){

    }

    public DailyMenu(DailyMenuEntity dailyMenuEntity){
        BeanUtils.copyProperties(
                dailyMenuEntity,
                this,
                "plannersIn", "meals");
        this.setMeals(dailyMenuEntity.getMealEntities().stream()
                .map(Meal::new)
                .collect(Collectors.toList()));
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Planner> getPlannersIn() {
        return plannersIn;
    }

    public void setPlannersIn(List<Planner> plannersIn) {
        this.plannersIn = plannersIn;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", day='" + day + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", meals='" + meals +
                '}';
    }


}

package upm.tfm.rvm.merkado_api.rest.dtos;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Planner {
    private String id;
    private String userId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private List<DailyMenu> dailyMenus;

    public Planner(){

    }
    public Planner(PlannerEntity plannerEntity){
        BeanUtils.copyProperties(plannerEntity, this, "dailyMenuEntities");
        this.setDailyMenus(
                plannerEntity.getDailyMenuEntities().stream()
                        .map(DailyMenu::new)
                        .collect(Collectors.toList())
        );
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus){
        this.dailyMenus = dailyMenus;
    }

    public String getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate" + creationDate + '\'' +
                ", dailyMenus=" + dailyMenus +
                '}';
    }
}

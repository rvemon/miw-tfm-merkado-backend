package upm.tfm.rvm.merkado_api.data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="planner")
public class PlannerEntity {

    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private LocalDate creationDate;

    @ManyToMany
    @JoinTable(
            name="planner_menus",
            joinColumns = @JoinColumn(name="planner_id"),
            inverseJoinColumns = @JoinColumn(name = "daily_menu_id")
    )
    private List<DailyMenuEntity> dailyMenuEntities;

    public PlannerEntity(){

    }

    public PlannerEntity(String userId, String name, String description,
                         List<DailyMenuEntity> dailyMenuEntities, LocalDate creationDate){
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.dailyMenuEntities = dailyMenuEntities;
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DailyMenuEntity> getDailyMenuEntities() {
        return dailyMenuEntities;
    }

    public void setDailyMenuEntities(List<DailyMenuEntity> dailyMenuEntities) {
        this.dailyMenuEntities = dailyMenuEntities;
    }

    public void addDailyMenu(DailyMenuEntity dailyMenu) {
        this.dailyMenuEntities.add(dailyMenu);
    }

}

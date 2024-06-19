package upm.tfm.rvm.merkado_api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="daily_menu")
public class DailyMenuEntity {

    @Id
    private String id;
    private String userId;
    private String name;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "dailyMenuEntities"
    )
    private List<PlannerEntity> plannerEntities;

    public DailyMenuEntity(){

    }
    public DailyMenuEntity(String userId, String name, List<PlannerEntity> plannerEntities) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.plannerEntities = plannerEntities;
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
}

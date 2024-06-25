package upm.tfm.rvm.merkado_api.rest.dtos;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Planner {
    private String id;
    private String userId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private List<DailyMenu> dailyMenus;

    public Planner(){

    }

    public Planner(String userId, String name, String description,
                   LocalDate creationDate, List<DailyMenu> dailyMenus) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.dailyMenus = dailyMenus;
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

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public void setDescription(String description) {
        this.description = description;
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

package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;

import java.util.List;

public class DailyMenu {
    private String id;
    private String name;
    private String userId;
    private String day;

    @JsonIgnore
    private List<Planner> plannersIn;

    public DailyMenu(){

    }

    public DailyMenu(DailyMenuEntity dailyMenuEntity){
        BeanUtils.copyProperties(dailyMenuEntity, this, "plannersIn");
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

    @Override
    public String toString() {
        return "DailyMenu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }


}

package upm.tfm.rvm.merkado_api.rest.dtos;

public class PlannerDailyMenu {
    private String plannerId;
    private String dailyMenuId;

    public String getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(String plannerId) {
        this.plannerId = plannerId;
    }

    public String getDailyMenuId() {
        return dailyMenuId;
    }

    public void setDailyMenuId(String dailyMenuId) {
        this.dailyMenuId = dailyMenuId;
    }
}

package upm.tfm.rvm.merkado_api.rest.dtos;

public class DailyMenuMeal {
    private String dailyMenuId;
    private String mealId;

    public String getDailyMenuId() {
        return dailyMenuId;
    }

    public void setDailyMenuId(String dailyMenuId) {
        this.dailyMenuId = dailyMenuId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}

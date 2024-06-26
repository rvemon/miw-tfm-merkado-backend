package upm.tfm.rvm.merkado_api.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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
        if(dailyMenuEntity.getMealEntities() != null){
            this.setMeals(dailyMenuEntity.getMealEntities().stream()
                    .map(Meal::new)
                    .collect(Collectors.toList()));
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPlannersIn(List<Planner> plannersIn) {
        this.plannersIn = plannersIn;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
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

    @JsonIgnore
    public List<ShoppingItem> getShoppingList(){
        List<ShoppingItem> shoppingList = new ArrayList<>();
        for(Meal meal: this.getMeals()){
            shoppingList  = mergeList(shoppingList , meal.getShoppingList());
        }
        return shoppingList;
    }

    public List<ShoppingItem> mergeList(List<ShoppingItem> list1, List<ShoppingItem> list2){
        for(ShoppingItem item: list2){
                    ShoppingItem current = list1.stream()
                            .filter(l -> l.getIngredient().getId()
                                    .equals(item.getIngredient().getId())).findFirst().orElse(null);
                    if(current != null){
                        current.setQuantity(current.getQuantity()+item.getQuantity());
                    }else{
                        list1.add(item);
                    }
        }
        return list1;

    }


}

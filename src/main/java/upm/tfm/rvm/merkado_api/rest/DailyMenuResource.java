package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.DailyMenu;
import upm.tfm.rvm.merkado_api.rest.dtos.Meal;
import upm.tfm.rvm.merkado_api.service.DailyMenuService;
import upm.tfm.rvm.merkado_api.service.MealService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(DailyMenuResource.DAILY_MENU)
public class DailyMenuResource {
    static final String DAILY_MENU = "/daily-menus";
    static final String DAILY_MENU_BY_USERID = "/userid/{id}";
    static final String ID_ID = "/{id}";

    private DailyMenuService dailyMenuService;
    private MealService mealService;

    @Autowired
    public DailyMenuResource(DailyMenuService dailyMenuService, MealService mealService){
        this.dailyMenuService= dailyMenuService;
        this.mealService = mealService;
    }

    @GetMapping(DAILY_MENU_BY_USERID)
    public List<DailyMenu> findAllByUserId(
            @PathVariable String id
        ){
        Stream<DailyMenuEntity> pe= this.dailyMenuService.findAllByUserId(id);
        return pe.map(DailyMenu::new).collect(Collectors.toList());

    }

    private List<MealEntity> getMealList(DailyMenu dailyMenu){
        List<String> mealIds = dailyMenu.getMeals().stream()
                .map(Meal::getId).collect(Collectors.toList());

        return this.mealService.findAll()
                .stream().filter(d-> mealIds.contains(d.getId()))
                .collect(Collectors.toList());

    }

    @PostMapping
    public DailyMenu create(DailyMenu dailyMenu){
        DailyMenuEntity dailyMenuEntity = new DailyMenuEntity(
                dailyMenu.getUserId(),
                dailyMenu.getName(),
                null,
                getMealList(dailyMenu),
                LocalDate.now(),
                dailyMenu.getDay()
                );
        return new DailyMenu(this.dailyMenuService.save(dailyMenuEntity));
    }

    @GetMapping(ID_ID)
    public DailyMenu getDailyMenuById(@PathVariable  String id){
        DailyMenuEntity dailyMenuEntity= this.dailyMenuService.getOne(id);
        if(dailyMenuEntity!=null){
            return new DailyMenu(dailyMenuEntity);
        }
        return null;
    }

    @PutMapping
    public DailyMenu update(DailyMenu dailyMenu){
        DailyMenuEntity dailyMenuEntity = this.dailyMenuService.getOne(dailyMenu.getId());
        if(dailyMenuEntity!=null){
            dailyMenuEntity.setName(dailyMenu.getName());
            dailyMenuEntity.setDay(dailyMenu.getDay());
            dailyMenuEntity.setMealEntities(getMealList(dailyMenu));
        }

        this.dailyMenuService.save(dailyMenuEntity);
        return dailyMenu;
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id){
        this.dailyMenuService.delete(id);
    }




}

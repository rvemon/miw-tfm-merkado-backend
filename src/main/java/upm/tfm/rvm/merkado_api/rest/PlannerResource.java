package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.Planner;
import upm.tfm.rvm.merkado_api.rest.dtos.PlannerDailyMenu;
import upm.tfm.rvm.merkado_api.service.DailyMenuService;
import upm.tfm.rvm.merkado_api.service.PlannerService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(PlannerResource.PLANNERS)
public class PlannerResource {
    static final String PLANNERS = "/planners";
    static final String PLANNERS_BY_USERID = "/userid/{id}";
    private PlannerService plannerService;
    private DailyMenuService dailyMenuService;

    @Autowired
    public PlannerResource(PlannerService plannerService, DailyMenuService dailyMenuService){
        this.plannerService = plannerService;
        this.dailyMenuService = dailyMenuService;
    }

    @GetMapping(PLANNERS_BY_USERID)
    public List<Planner> findAllByUserId(
            @PathVariable String id
    ){
        Stream<PlannerEntity> pe= this.plannerService.findAllByUserId(id);
        //List<PlannerEntity> pl = pe.collect(Collectors.toList());
        return pe.map(Planner::new).collect(Collectors.toList());

    }

    @PostMapping("/set-planner-daily-menu")
    public Planner addDailyMenu(
            @RequestBody PlannerDailyMenu plannerDailyMenu
            ){
        PlannerEntity planner = plannerService.getOne(plannerDailyMenu.getPlannerId());
        DailyMenuEntity dailyMenu = dailyMenuService.getOne(plannerDailyMenu.getDailyMenuId());
        planner.addDailyMenu(dailyMenu);
        return new Planner(plannerService.save(planner));
    }

    @GetMapping("/test")
    public String test(){

        PlannerEntity planner =
                new PlannerEntity("1","planner 2",
                        "descripcion planner 1", null, LocalDate.now());

        DailyMenuEntity dailyMenu = new DailyMenuEntity("1", "daily menu 2",
                null, null, LocalDate.now(), "MONDAY");
        plannerService.save(planner);
        dailyMenuService.save(dailyMenu);
        return "test";
    }
}

package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.DailyMenu;
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
    static final String ID_ID = "/{id}";
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
        return pe.map(Planner::new).collect(Collectors.toList());

    }

    private List<DailyMenuEntity> getDailyMenuList(Planner planner){
        if(planner.getDailyMenus() != null){
            List<String>  dailyMenuIds = planner.getDailyMenus().stream()
                    .map(DailyMenu::getId).collect(Collectors.toList());

            return this.dailyMenuService.findAll()
                    .stream().filter(d-> dailyMenuIds.contains(d.getId()))
                    .collect(Collectors.toList());
        }

        return null;
    }

    @PostMapping
    public Planner create(@RequestBody Planner planner){
        PlannerEntity plannerEntity = new PlannerEntity(
                planner.getUserId(),
                planner.getName(),
                planner.getDescription(),
                getDailyMenuList(planner),
                LocalDate.now()
        );
        return new Planner(this.plannerService.save(plannerEntity));
    }

    @GetMapping(ID_ID)
    public Planner getPlannerById(@PathVariable  String id){
        PlannerEntity plannerEntity = this.plannerService.getOne(id);
        if(plannerEntity!=null){
            return new Planner(plannerEntity);
        }
        return null;
    }

    @PutMapping(ID_ID)
    public Planner update(@RequestBody Planner planner){
        PlannerEntity plannerEntity = this.plannerService.getOne(planner.getId());
        if(plannerEntity!=null){
            plannerEntity.setName(planner.getName());
            plannerEntity.setDescription(planner.getDescription());
            plannerEntity.setDailyMenuEntities(getDailyMenuList(planner));
        }

        this.plannerService.save(plannerEntity);
        return new Planner(plannerEntity);
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id){
        this.plannerService.delete(id);
    }


}

package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;
import upm.tfm.rvm.merkado_api.data.PlannerRepository;

import java.util.stream.Stream;

@Service
public class PlannerService {
    private PlannerRepository plannerRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public Stream<PlannerEntity> findAllByUserId(String userId) {
        return this.plannerRepository.findAll().stream()
                .filter(planner -> userId.equals(planner.getUserId()));
    }

    public PlannerEntity getOne(String id){
        return this.plannerRepository.getOne(id);
    }

    public PlannerEntity save(PlannerEntity plannerEntity){
        return this.plannerRepository.save(plannerEntity);
    }

    public void delete(String plannerId){
        PlannerEntity planner = this.plannerRepository.findById(plannerId).orElse(null);
        if(planner != null){
            this.plannerRepository.delete(planner);
        }
    }

    public PlannerEntity update(PlannerEntity plannerEntity){
        PlannerEntity planner = this.plannerRepository.findById(plannerEntity.getId()).orElse(null);
        if(planner!=null){
            planner.setName(plannerEntity.getName());
            planner.setDescription(plannerEntity.getDescription());
            planner.setDailyMenuEntities(plannerEntity.getDailyMenuEntities());
            this.plannerRepository.save(planner);
            return planner;
        }
        return null;
    }

    //TODO shopping list
}

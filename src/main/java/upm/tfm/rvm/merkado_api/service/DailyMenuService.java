package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.DailyMenuRepository;
import upm.tfm.rvm.merkado_api.data.PlannerEntity;

import java.util.List;
import java.util.stream.Stream;

@Service
public class DailyMenuService {
    private DailyMenuRepository dailyMenuRepository;

    @Autowired
    public DailyMenuService(DailyMenuRepository dailyMenuRepository){
        this.dailyMenuRepository = dailyMenuRepository;
    }

    public List<DailyMenuEntity> findAll(){
        return this.dailyMenuRepository.findAll();
    }

    public DailyMenuEntity getOne(String id){
        return this.dailyMenuRepository.getOne(id);
    }

    public DailyMenuEntity save(DailyMenuEntity dailyMenu) {
        return this.dailyMenuRepository.save(dailyMenu);
    }

    public Stream<DailyMenuEntity> findAllByUserId(String userId) {
        return this.dailyMenuRepository.findAll().stream()
                .filter(dailyMenu -> userId.equals(dailyMenu.getUserId()));
    }

    public void delete(String dailyMenuId){
        DailyMenuEntity dailyMenu = this.dailyMenuRepository.findById(dailyMenuId).orElse(null);
        if(dailyMenu!=null){
            this.dailyMenuRepository.delete(dailyMenu);
        }
    }

}

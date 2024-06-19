package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.DailyMenuEntity;
import upm.tfm.rvm.merkado_api.data.DailyMenuRepository;

@Service
public class DailyMenuService {
    private DailyMenuRepository dailyMenuRepository;

    @Autowired
    public DailyMenuService(DailyMenuRepository dailyMenuRepository){
        this.dailyMenuRepository = dailyMenuRepository;
    }

    public DailyMenuEntity getOne(String id){
        return this.dailyMenuRepository.getOne(id);
    }

    public DailyMenuEntity save(DailyMenuEntity dailyMenu) {
        return this.dailyMenuRepository.save(dailyMenu);
    }
}

package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.MealEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientEntity;
import upm.tfm.rvm.merkado_api.data.MealIngredientRepository;
import upm.tfm.rvm.merkado_api.data.MealRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MealService {
    private MealRepository mealRepository;
    private MealIngredientRepository mealIngredientRepository;

    @Autowired
    public MealService(MealRepository mealRepository, MealIngredientRepository mealIngredientRepository){
        this.mealRepository = mealRepository;
        this.mealIngredientRepository = mealIngredientRepository;
    }
    public List<MealEntity> findAll(){
        return this.mealRepository.findAll();
    }

    public void deleteAll(){
        this.mealIngredientRepository.deleteAll();
        this.mealRepository.deleteAll();
    }

    public void saveMealIngredient(MealIngredientEntity mealIngredientEntity) {
         this.mealIngredientRepository.save(mealIngredientEntity);
    }

    public Stream<MealEntity> findAllByUserId(String userId) {
        return this.mealRepository.findAll().stream()
                .filter(meal -> userId.equals(meal.getUserId()));
    }

    public MealEntity getOne(String id){
        return this.mealRepository.getOne(id);
    }

    public MealEntity save(MealEntity meal) {
        return this.mealRepository.save(meal);
    }

    public void delete(String mealId){
        MealEntity meal = this.mealRepository.findById(mealId).orElse(null);
        if(meal!=null){
            this.mealRepository.delete(meal);
        }
    }

    public void deleteIngredient(String id) {
        MealIngredientEntity mealIngredient = this.mealIngredientRepository.findById(id).orElse(null);
        if(mealIngredient!=null){
            this.mealIngredientRepository.delete(mealIngredient);
        }
    }
}

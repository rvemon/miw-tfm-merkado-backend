package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.IngredientEntity;
import upm.tfm.rvm.merkado_api.data.IngredientRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class IngredientService {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public Stream<IngredientEntity> findAllByUserId(String userId) {
        return this.ingredientRepository.findAll().stream()
                .filter(ingredient -> userId.equals(ingredient.getUserId()));
    }

    public List<IngredientEntity> findAll(){
        return this.ingredientRepository.findAll();
    }

    public IngredientEntity getOne(String id){
        return this.ingredientRepository.getOne(id);
    }

    public IngredientEntity save(IngredientEntity ingredient) {
        return this.ingredientRepository.save(ingredient);
    }

    public void delete(String ingredientId){
        IngredientEntity ingredient = this.ingredientRepository.findById(ingredientId).orElse(null);
        if(ingredient!=null){
            this.ingredientRepository.delete(ingredient);
        }
    }
}

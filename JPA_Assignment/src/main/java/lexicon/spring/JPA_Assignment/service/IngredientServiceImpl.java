package lexicon.spring.JPA_Assignment.service;


import lexicon.spring.JPA_Assignment.data.IngredientRepository;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.IngredientForm;
import lexicon.spring.JPA_Assignment.model.entity.Ingredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl  implements IngredientService{
    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;
    @Autowired
    public IngredientServiceImpl(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientForm create(IngredientForm ingredientForm) {
        Ingredient ingredient=modelMapper.map(ingredientForm,Ingredient.class);
        Ingredient ingredientSaved=ingredientRepository.save(ingredient);
        IngredientForm ingredientFormMapper=modelMapper.map(ingredientSaved, IngredientForm.class);
        return ingredientFormMapper;
    }

    @Override
    public void update(IngredientForm ingredientForm, Integer ingredientId) {
        if(ingredientId==null) throw new IllegalArgumentException("Ingredient Id is null");
        if(ingredientRepository.existsById(ingredientId)){
            Ingredient ingredient=modelMapper.map(ingredientForm,Ingredient.class);
            ingredientRepository.save(ingredient);
        }
    }

    @Override
    public void deleteById(Integer ingredientId) {
        if(ingredientId==null) throw new IllegalArgumentException("Ingredient Id is null");
        if(ingredientRepository.existsById(ingredientId)){
            ingredientRepository.deleteById(ingredientId);
        }
    }

    @Override
    public List<IngredientForm> findAll() {
        Iterable<Ingredient> ingredientList=ingredientRepository.findAll();
        List<IngredientForm> ingredientFormList=modelMapper.map(ingredientList
                ,new TypeToken<List<IngredientForm>>(){}.getType());
        return ingredientFormList;
    }

    @Override
    public IngredientForm findById(Integer ingredientId) {
        if(ingredientId==null)throw new IllegalArgumentException("Ingredient Id is null");
        Ingredient foundById=ingredientRepository.findById(ingredientId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Id not found"));

        return modelMapper.map(foundById,IngredientForm.class);
    }
}

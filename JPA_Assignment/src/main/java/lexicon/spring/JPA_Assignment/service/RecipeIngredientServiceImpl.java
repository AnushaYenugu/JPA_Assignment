package lexicon.spring.JPA_Assignment.service;

import lexicon.spring.JPA_Assignment.data.RecipeIngredientRepository;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.RecipeIngredientForm;
import lexicon.spring.JPA_Assignment.model.dto.RecipeInstructionForm;
import lexicon.spring.JPA_Assignment.model.entity.RecipeIngredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ModelMapper modelMapper;
@Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository, ModelMapper modelMapper) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeIngredientForm create(RecipeIngredientForm recipeIngredientForm) {
        RecipeIngredient recipeIngredient=modelMapper.map(recipeIngredientForm, RecipeIngredient.class);
        RecipeIngredient savedRecipeIngredient=recipeIngredientRepository.save(recipeIngredient);
        RecipeIngredientForm recipeIngredientFormMapped=modelMapper.map(savedRecipeIngredient,RecipeIngredientForm.class);
        return recipeIngredientFormMapped;
    }

    @Override
    public void update(RecipeIngredientForm recipeIngredientForm, Integer recipeIngredientId) {
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Ingredient id is null");
        if(recipeIngredientRepository.existsById(recipeIngredientId)){
            RecipeIngredient recipeIngredient=modelMapper.map(recipeIngredientForm,RecipeIngredient.class);
            recipeIngredientRepository.save(recipeIngredient);
       }
}

    @Override
    public void deleteById(Integer recipeIngredientId) {
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Ingredient Id is null");
        if(recipeIngredientRepository.existsById(recipeIngredientId)) {
            recipeIngredientRepository.deleteById(recipeIngredientId);
        }

    }

    @Override
    public List<RecipeIngredientForm> findAll() {
        Iterable<RecipeIngredient> recipeIngredientList=recipeIngredientRepository.findAll();
        List<RecipeIngredientForm> recipeIngredientFormList=modelMapper.map(recipeIngredientList
                ,new TypeToken<List<RecipeIngredientForm>>(){}.getType());
        return recipeIngredientFormList;
    }

    @Override
    public RecipeIngredientForm findById(Integer recipeIngredientId) {
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Id is null");
        RecipeIngredient foundByRecipeIngredientId=recipeIngredientRepository.findById(recipeIngredientId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Ingredient Resource Not Found"));

        return modelMapper.map(foundByRecipeIngredientId,RecipeIngredientForm.class);
    }
}

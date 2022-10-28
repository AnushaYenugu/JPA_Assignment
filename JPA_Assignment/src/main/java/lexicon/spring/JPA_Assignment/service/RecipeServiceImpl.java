package lexicon.spring.JPA_Assignment.service;

import lexicon.spring.JPA_Assignment.data.RecipeRepository;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.RecipeForm;
import lexicon.spring.JPA_Assignment.model.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(ModelMapper modelMapper, RecipeRepository recipeRepository) {
        this.modelMapper = modelMapper;
        this.recipeRepository = recipeRepository;
    }
    @Transactional
    @Override
    public RecipeForm create(RecipeForm recipeForm) {
        Recipe recipe=modelMapper.map(recipeForm,Recipe.class);
       recipe.getRecipeIngredients().forEach(recipeIngredient -> recipeIngredient.addRecipe(recipe));
        Recipe savedRecipe=recipeRepository.save(recipe);
        RecipeForm recipeFormMapped=modelMapper.map(savedRecipe,RecipeForm.class);
        return recipeFormMapped;
    }

    @Override
    public void update(RecipeForm recipeForm, Integer recipeId) {
        if(recipeId==null) throw new IllegalArgumentException("Recipe id is null");
        if(recipeRepository.existsById(recipeId)){
            Recipe recipe=modelMapper.map(recipeForm,Recipe.class);
            recipeRepository.save(recipe);

        }
    }

    @Override
    public void deleteById(Integer recipeId) {
        if(recipeId==null) throw new IllegalArgumentException("Recipe Id is null");
        if(recipeRepository.existsById(recipeId)){
            recipeRepository.deleteById(recipeId);
        }
    }


    @Override
    public List<RecipeForm> findAll() {
        Iterable<Recipe> recipeList=recipeRepository.findAll();
        return modelMapper.map(recipeList
                ,new TypeToken<List<RecipeForm>>(){}.getType()
        );
    }

    @Override
    public RecipeForm findById(Integer recipeId) {
        if(recipeId==null) throw new IllegalArgumentException("Recipe Id is null");
        Recipe foundByRecipeId=recipeRepository.findById(recipeId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Resource Not Found"));
        return modelMapper.map(foundByRecipeId,RecipeForm.class);
    }


}

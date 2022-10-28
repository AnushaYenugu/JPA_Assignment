package lexicon.spring.JPA_Assignment.service;


import lexicon.spring.JPA_Assignment.data.RecipeCategoryRepository;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.RecipeCategoryForm;
import lexicon.spring.JPA_Assignment.model.entity.RecipeCategory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeCategoryServiceImpl implements RecipeCategoryService{
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public RecipeCategoryServiceImpl(RecipeCategoryRepository recipeCategoryRepository, ModelMapper modelMapper) {
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeCategoryForm create(RecipeCategoryForm recipeCategoryForm) {
        RecipeCategory recipeCategory=modelMapper.map(recipeCategoryForm,RecipeCategory.class);
        RecipeCategory savedRecipeCategory=recipeCategoryRepository.save(recipeCategory);
        RecipeCategoryForm recipeCategoryFormMapped=modelMapper.map(savedRecipeCategory,RecipeCategoryForm.class);
        return recipeCategoryFormMapped;
    }

    @Override
    public void update(RecipeCategoryForm recipeCategoryForm, Integer categoryId) {
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");

        if(recipeCategoryRepository.existsById(categoryId)){
            //  if(categoryId==recipeCategoryForm.getCategoryId()){
            RecipeCategory recipeCategory=modelMapper.map(recipeCategoryForm,RecipeCategory.class);
            recipeCategoryRepository.save(recipeCategory);
        }
    }

    @Override
    public void deleteById(Integer categoryId) {
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");
        if(recipeCategoryRepository.existsById(categoryId)) {
            recipeCategoryRepository.deleteById(categoryId);
        }

    }

    @Override
    public List<RecipeCategoryForm> findAll() {
        Iterable<RecipeCategory> recipeCategoryList=recipeCategoryRepository.findAll();
        List<RecipeCategoryForm> recipeCategoryFormList=modelMapper.map(recipeCategoryList,
                new TypeToken<List<RecipeCategoryForm>>(){}.getType());

        return recipeCategoryFormList;
    }

    @Override
    public RecipeCategoryForm findById(Integer categoryId) {
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");
        RecipeCategory foundByRecipeCategoryId=recipeCategoryRepository.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category Resource not Found"));

        return modelMapper.map(foundByRecipeCategoryId,RecipeCategoryForm.class);
    }
}

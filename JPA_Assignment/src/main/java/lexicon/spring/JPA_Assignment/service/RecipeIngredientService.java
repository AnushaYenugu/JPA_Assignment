package lexicon.spring.JPA_Assignment.service;


import lexicon.spring.JPA_Assignment.model.dto.RecipeIngredientForm;

import java.util.List;

public interface RecipeIngredientService {
    RecipeIngredientForm create(RecipeIngredientForm Form);

    void update(RecipeIngredientForm form, Integer id);

    void deleteById(Integer id);

    List<RecipeIngredientForm> findAll();

    RecipeIngredientForm findById(Integer id);

}

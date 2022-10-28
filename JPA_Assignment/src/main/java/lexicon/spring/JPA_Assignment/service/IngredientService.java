package lexicon.spring.JPA_Assignment.service;

import lexicon.spring.JPA_Assignment.model.dto.IngredientForm;

import java.util.List;

public interface IngredientService {
    IngredientForm create(IngredientForm Form);

    void update(IngredientForm form, Integer id);

    void deleteById(Integer id);

    List<IngredientForm> findAll();

    IngredientForm findById(Integer id);

}

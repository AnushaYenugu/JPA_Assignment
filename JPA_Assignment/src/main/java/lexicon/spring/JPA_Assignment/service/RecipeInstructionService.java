package lexicon.spring.JPA_Assignment.service;



import lexicon.spring.JPA_Assignment.model.dto.RecipeInstructionForm;

import java.util.List;

public interface RecipeInstructionService {
    RecipeInstructionForm create(RecipeInstructionForm Form);

    void update(RecipeInstructionForm form, Integer id);

    void deleteById(Integer id);

    List<RecipeInstructionForm> findAll();

    RecipeInstructionForm findById(Integer id);

}

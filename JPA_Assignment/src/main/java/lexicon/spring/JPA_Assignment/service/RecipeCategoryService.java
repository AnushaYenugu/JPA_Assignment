package lexicon.spring.JPA_Assignment.service;



import lexicon.spring.JPA_Assignment.model.dto.RecipeCategoryForm;

import java.util.List;

public interface RecipeCategoryService {
    RecipeCategoryForm create(RecipeCategoryForm Form);

    void update(RecipeCategoryForm form, Integer id);

    void deleteById(Integer id);

    List<RecipeCategoryForm> findAll();

    RecipeCategoryForm findById(Integer id);

}

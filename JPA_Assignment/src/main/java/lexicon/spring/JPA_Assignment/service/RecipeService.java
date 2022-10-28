package lexicon.spring.JPA_Assignment.service;




import lexicon.spring.JPA_Assignment.model.dto.RecipeForm;


import java.util.List;

public interface RecipeService {

    RecipeForm create(RecipeForm Form);

    void update(RecipeForm form, Integer id);

    void deleteById(Integer id);

    List<RecipeForm> findAll();

    RecipeForm findById(Integer id);

}




package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient,Integer> {



}

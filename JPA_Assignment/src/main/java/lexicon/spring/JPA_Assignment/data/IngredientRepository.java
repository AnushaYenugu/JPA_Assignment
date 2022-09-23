package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.Ingredient;
import lexicon.spring.JPA_Assignment.model.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient,Integer> {

   Ingredient findIngredientByIngredientNameIgnoreCase(String ingredientName);
   List<Ingredient> findIngredientsByIngredientNameContainsIgnoreCase(String ingredientName);
}

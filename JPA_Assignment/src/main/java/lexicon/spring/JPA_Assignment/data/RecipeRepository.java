package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe,Integer> {
   Recipe findRecipeByRecipeName(String recipeName);

   Collection<Recipe> findRecipesByRecipeIngredientsIngredientIngredientName(String ingredientName);




 //  Set<Recipe> findRecipesByCategoriesRecipeCategoryCategory(String category);

   Collection<Recipe> findRecipesByCategoriesCategory(String category);
   Recipe findRecipeByRecipeId(int id);

}

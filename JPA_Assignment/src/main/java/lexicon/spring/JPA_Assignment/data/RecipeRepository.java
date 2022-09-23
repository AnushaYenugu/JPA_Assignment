package lexicon.spring.JPA_Assignment.data;

import antlr.collections.List;
import lexicon.spring.JPA_Assignment.model.Recipe;
import lexicon.spring.JPA_Assignment.model.RecipeCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Locale;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe,Integer> {
   Recipe findRecipeByRecipeName(String recipeName);

   Set<Recipe> findRecipesByRecipeIngredientsIngredientIngredientName(String ingredientName);




 //  Set<Recipe> findRecipesByCategoriesRecipeCategoryCategory(String category);

   Set<Recipe> findRecipesByCategoriesCategory(String category);
   Recipe findRecipeByRecipeId(int id);

}

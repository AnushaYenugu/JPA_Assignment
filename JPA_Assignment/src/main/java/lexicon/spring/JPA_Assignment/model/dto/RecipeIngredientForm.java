package lexicon.spring.JPA_Assignment.model.dto;

import lexicon.spring.JPA_Assignment.model.entity.Ingredient;
import lexicon.spring.JPA_Assignment.model.entity.Measurement;
import lexicon.spring.JPA_Assignment.model.entity.Recipe;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeIngredientForm {
 //RecipeForm recipeForRecipeIngredients;
   // private int recipeIngredientId;
    private double amount;
    private Measurement measurement;
    IngredientForm ingredient;
    //Recipe recipe;
  // int recipeIdForIngredient;
}

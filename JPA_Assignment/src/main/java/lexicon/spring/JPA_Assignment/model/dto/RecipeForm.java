package lexicon.spring.JPA_Assignment.model.dto;

import lexicon.spring.JPA_Assignment.model.entity.RecipeCategory;
import lexicon.spring.JPA_Assignment.model.entity.RecipeIngredient;
import lexicon.spring.JPA_Assignment.model.entity.RecipeInstruction;
import lombok.*;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class RecipeForm {
   //private int recipeId;
    private String recipeName;
    // RecipeInstruction recipeInstruction;
    RecipeInstructionForm recipeInstruction;
    List<RecipeIngredientForm> recipeIngredients;
    Collection<RecipeCategoryForm> categories = new ArrayList<>();

}

package lexicon.spring.JPA_Assignment.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RecipeInstruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeInstructionId;
    private String instructions;

    public RecipeInstruction() {
    }

    public RecipeInstruction(String instructions) {
        this.instructions = instructions;
    }

    public RecipeInstruction(int recipeInstructionId, String instructions) {
        this.recipeInstructionId = recipeInstructionId;
        this.instructions = instructions;
    }

    public void addRecipe(Recipe recipe){
        if(recipe==null) throw new IllegalArgumentException("Recipe is null");
        recipe.setRecipeInstruction(this);
    }

    public int getId() {
        return recipeInstructionId;
    }

    public void setId(int recipeInstructionId) {
        this.recipeInstructionId = recipeInstructionId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstruction that = (RecipeInstruction) o;
        return recipeInstructionId == that.recipeInstructionId && instructions.equals(that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeInstructionId, instructions);
    }

    @Override
    public String toString() {
        return "RecipeInstruction{" +
                "id=" + recipeInstructionId +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}

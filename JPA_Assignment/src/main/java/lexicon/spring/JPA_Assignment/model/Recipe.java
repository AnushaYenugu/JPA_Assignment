package lexicon.spring.JPA_Assignment.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_instruction_id")
    RecipeInstruction recipeInstruction;

    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
            List<RecipeIngredient> recipeIngredients;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "category_recipe", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<RecipeCategory> categories = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstruction, Set<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstruction = recipeInstruction;
        this.categories = categories;
    }

    public Recipe(int recipeId, String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstruction, Set<RecipeCategory> categories) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstruction = recipeInstruction;
        this.categories = categories;
    }
    //Convince Methods

    public void addRecipeInstruction(RecipeInstruction instruction){
        if(instruction==null) throw new IllegalArgumentException("Recipe Instruction Object is null");
        instruction.addRecipe(this);
    }

public void addRecipeIngredients(RecipeIngredient recipeIngredient){
        if(recipeIngredient==null) throw new IllegalArgumentException("Recipe Ingredient is null");
        if(recipeIngredients==null) recipeIngredients=new ArrayList<>();
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
}

public void addRecipeCategory(RecipeCategory recipeCategory){
        if(recipeCategory==null)throw new IllegalArgumentException("Recipe Category is null");
        if(categories==null) categories=new HashSet<>();
        categories.add(recipeCategory);
        recipeCategory.getRecipe().add(this);
}


    //Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeInstruction getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<RecipeCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }
}

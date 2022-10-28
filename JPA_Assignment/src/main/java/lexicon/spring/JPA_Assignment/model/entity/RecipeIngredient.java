package lexicon.spring.JPA_Assignment.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeIngredientId;
    private double amount;
    private Measurement measurement;
//REmoved CascadeType.Detach
    @ManyToOne(cascade = {CascadeType.ALL,CascadeType.REFRESH,CascadeType.DETACH})
    Ingredient ingredient;
    @ManyToOne(cascade = {CascadeType.ALL,CascadeType.REFRESH})
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(double amount,Measurement measurement) {

        this.amount = amount;
        this.measurement=measurement;
    }

    public RecipeIngredient(double amount, Measurement measurement, Ingredient ingredient, Recipe recipe) {
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public RecipeIngredient(double amount, Measurement measurement, Ingredient ingredient) {
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
    }

    public RecipeIngredient(int recipeIngredientId, double amount, Measurement measurement, Ingredient ingredient, Recipe recipe) {
        this.recipeIngredientId = recipeIngredientId;
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    //Convience methods

    public void addIngredient(Ingredient ingredientToBeAdded){
        if(ingredientToBeAdded==null) throw new IllegalArgumentException("Ingredient is null");
          ingredientToBeAdded.addRecipeIngredient(this);
    }
    public void addRecipe(Recipe recipeToBeAdded){
        if(recipeToBeAdded==null) throw new IllegalArgumentException("Ingredient is null");
      //  recipeToBeAdded.addRecipeIngredients(this);
        this.setRecipe(recipeToBeAdded);

    }
    public void removeIngredient(Ingredient ingredientToBeAdded){
        if(ingredientToBeAdded==null) throw new IllegalArgumentException("Ingredient is null");

    }



    //Getters and setters

    public int getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(int recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(recipeIngredientId, that.recipeIngredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId, amount);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeIngredientId='" + recipeIngredientId + '\'' +
                ", amount=" + amount +
                ", measurement=" + measurement +
                '}';
    }
}

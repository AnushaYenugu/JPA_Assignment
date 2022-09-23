package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    TestEntityManager testEntityManager;
    Recipe testRecipe;

    public List<Ingredient> ingredients() {
        return Arrays.asList(
                new Ingredient("tomato"),
                new Ingredient("tumeric spice"),
                new Ingredient("washed peas"),
                new Ingredient("rice"),
                //Palak Paneer Ingredients
                new Ingredient("Spinach") ,
                new Ingredient("Paneer"));
    }



    public Set<RecipeCategory> categories(){

        HashSet<RecipeCategory> result=new HashSet<RecipeCategory>();
        RecipeCategory vegan =new RecipeCategory("vegan");
        RecipeCategory vegetarian=new RecipeCategory("vegetarian");
         result.add(vegan);
         result.add(vegetarian);

        return result;

    }

    @BeforeEach
    void setUp() {
/**
        List<Ingredient> persistedIngredients = ingredients().stream().map(testEntityManager::persist).collect(Collectors.toList());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>(
                Arrays.asList(new RecipeIngredient(300, Measurement.G, persistedIngredients.get(3)),
                        new RecipeIngredient(250, Measurement.G, persistedIngredients.get(0)),
                        new RecipeIngredient(1, Measurement.TSP, persistedIngredients.get(1)),
                        new RecipeIngredient(100, Measurement.G, persistedIngredients.get(2))));


        List<RecipeIngredient> persistedRecipeIngredients = recipeIngredients.stream().map(testEntityManager::persist).collect(Collectors.toList());
        RecipeInstruction instruction = new RecipeInstruction("\"Cook rice first and in another pan add oil, salt, and mentioned ingredients,\" +\n" +
                "                \"and cook tomatoes and peas and add boiled rice\"");

        testEntityManager.persist(instruction);
        Set<RecipeCategory> persistedCategory = categories().stream().map(testEntityManager::persist).collect(Collectors.toSet());

        Recipe recipe=
                        new Recipe("Tomato Pulav", persistedRecipeIngredients, instruction, persistedCategory
                                .stream()
                                .filter(recipeCategory -> "vegan".equalsIgnoreCase(recipeCategory.getCategory()))
                                .collect(Collectors.toSet()));



      //  List<Recipe> persistedRecipes = recipes.stream().map(testEntityManager::persist).collect(Collectors.toList());

        recipeRepository.save(recipe);
        testEntityManager.flush();
**/

        List<Ingredient> persistedIngredients = ingredients().stream().map(testEntityManager::persist).collect(Collectors.toList());
        List<RecipeIngredient> recipeIngredients = new ArrayList<>(
                Arrays.asList(new RecipeIngredient(300, Measurement.G, persistedIngredients.get(3)),
                        new RecipeIngredient(250, Measurement.G, persistedIngredients.get(0)),
                        new RecipeIngredient(1, Measurement.TSP, persistedIngredients.get(1)),
                        new RecipeIngredient(100, Measurement.G, persistedIngredients.get(2))));
        List<RecipeIngredient> persistedRecipeIngredients = recipeIngredients.stream().map(testEntityManager::persist).collect(Collectors.toList());
        RecipeInstruction instruction = new RecipeInstruction("\"Cook rice first and in another pan add oil, salt, and mentioned ingredients,\" +\n" +
                "                \"and cook tomatoes and peas and add boiled rice\"");
        testEntityManager.persist(instruction);
        Set<RecipeCategory> persistedCategory = categories().stream().map(testEntityManager::persist).collect(Collectors.toSet());
        List<Recipe> recipes = new ArrayList<>(
                Arrays.asList(
                        new Recipe("Tomato Pulav", persistedRecipeIngredients, instruction, persistedCategory
                                .stream()
                                .filter(recipeCategory -> "vegan".equalsIgnoreCase(recipeCategory.getCategory()))
                                .collect(Collectors.toSet()))
                )
        );
        List<Recipe> persistedRecipes = recipes.stream().map(testEntityManager::persist).collect(Collectors.toList());
        Set<Recipe> found=recipeRepository.findRecipesByRecipeIngredientsIngredientIngredientName("rice");

    }



    @Test
    void findRecipeByRecipeName() {
        Recipe found=recipeRepository.findRecipeByRecipeName("Tomato Pulav");
        assertNotNull(found);
        assertEquals("Tomato Pulav",found.getRecipeName());

    }

    @Test
    void findRecipesByRecipeIngredientsIngredientIngredientName() {

        Set<Recipe> found=recipeRepository.findRecipesByRecipeIngredientsIngredientIngredientName("rice");
        System.out.println("anusha "+found.size());
        found.forEach(recipe -> System.out.println("hi "+recipe));

        assertEquals(1,found.size());
      //  assertEquals(1, found.stream()
        //        .filter(recipe -> "Tomato Pulav".equalsIgnoreCase(recipe.getRecipeName())).count());

    }

    @Test
    void findRecipesByCategoriesCategory() {

        Set<Recipe> found=recipeRepository.findRecipesByCategoriesCategory("vegan");
        assertEquals(1,found.size());
        found.stream().forEach(f -> System.out.println("hi rere"+f.getCategories()));
        //assertEquals("Tomato Pulav",found.stream().anyMatch(recipe -> recipe.getCategories()));
        assertNotNull(found.stream().anyMatch(recipe -> "Tomato pulav".equalsIgnoreCase(recipe.getRecipeName())));
    }

    @Test
    void findAll(){
     Collection<Recipe> resultFound = (Collection<Recipe>) recipeRepository.findAll();
      assertEquals(1, resultFound.size());

    }
}
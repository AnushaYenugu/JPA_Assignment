package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    RecipeInstructionRepository recipeInstructionRepository;
    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;


    @BeforeEach
    void setUp() {
        Ingredient tomato=new Ingredient("tomato");
        Ingredient turmeric=new Ingredient("tumeric spice");
        Ingredient peas=new Ingredient("washed peas");
        Ingredient rice=new Ingredient("rice");
        //Palak Paneer Ingredients
        Ingredient palak=new Ingredient("Spinach");
        Ingredient  paneer=new Ingredient("Paneer");

        ingredientRepository.save(tomato);
        ingredientRepository.save(turmeric);
        ingredientRepository.save(peas);
        ingredientRepository.save(rice);

        ingredientRepository.save(palak);
        ingredientRepository.save(paneer);

        System.out.println(ingredientRepository.findIngredientByIngredientNameIgnoreCase("tomato"));
        ingredientRepository.findIngredientsByIngredientNameContainsIgnoreCase("t").forEach(System.out::println);

        List<RecipeIngredient> tomatoPulavIngredients=new ArrayList<>();
        // Recipe tomatoPulavRecipe=new Recipe("Tomato Pulav");
        //Tomata pulav recipe Ingredients Measuremnt
        RecipeIngredient tomatoPulavRecipeIngredient=new RecipeIngredient(300, Measurement.G);
        RecipeIngredient tomatoPulavRecipeIngredient2=new RecipeIngredient(250, Measurement.G );
        RecipeIngredient tomatoPulavRecipeIngredient3=new RecipeIngredient(1, Measurement.TSP );
        RecipeIngredient tomatoPulavRecipeIngredient4=new RecipeIngredient(100, Measurement.G );
        tomatoPulavRecipeIngredient.addIngredient(rice);
        tomatoPulavRecipeIngredient2.addIngredient(tomato);
        tomatoPulavRecipeIngredient3.addIngredient(turmeric);
        tomatoPulavRecipeIngredient4.addIngredient(peas);


        //Palak Recipe Ingredients Measurement
        RecipeIngredient palakPaneerIngredient1=new RecipeIngredient(250,Measurement.G);
        RecipeIngredient palakPaneerIngredient2=new RecipeIngredient(200,Measurement.G);
        // RecipeIngredient palakPaneerIngredient3=new RecipeIngredient(100,Measurement.G);

        palakPaneerIngredient1.addIngredient(palak);
        palakPaneerIngredient2.addIngredient(paneer);
        //  palakPaneerIngredient2.addIngredient(tomato);

//Saving Recipeingredients for Tomato


        recipeIngredientRepository.save(tomatoPulavRecipeIngredient);
        recipeIngredientRepository.save(tomatoPulavRecipeIngredient2);
        recipeIngredientRepository.save(tomatoPulavRecipeIngredient3);
        recipeIngredientRepository.save(tomatoPulavRecipeIngredient4);

        //Saving Recipeingredients for Palak

        recipeIngredientRepository.save(palakPaneerIngredient1);
        recipeIngredientRepository.save(palakPaneerIngredient2);
        //   recipeIngredientRepository.save(palakPaneerIngredient3);


        tomatoPulavIngredients.add(tomatoPulavRecipeIngredient);
        tomatoPulavIngredients.add(tomatoPulavRecipeIngredient2);
        tomatoPulavIngredients.add(tomatoPulavRecipeIngredient3);
        tomatoPulavIngredients.add(tomatoPulavRecipeIngredient4);


        HashSet<RecipeCategory> recipeCategories=new HashSet<>();
        RecipeCategory vegan=new RecipeCategory("vegan");
        RecipeCategory vegitarian=new RecipeCategory("vegitarian");
        recipeCategoryRepository.save(vegan);
        recipeCategoryRepository.save(vegitarian);
        recipeCategories.add(vegan);
        recipeCategories.add(vegitarian);

        RecipeInstruction tomatoRecipeInstruction=new RecipeInstruction("Cook rice first and in another pan add oil, salt, and mentioned ingredients," +
                "and cook tomatoes and peas and add boiled rice");
        recipeInstructionRepository.save(tomatoRecipeInstruction);

        RecipeInstruction palakRecipeInstruction=new RecipeInstruction("Boil water and blanch washed spinach and let it coll and blend it and add all ingredients and spinach and paneer and cook it for 10 minutes");
        recipeInstructionRepository.save(palakRecipeInstruction);



        Recipe tomatoPulav=new Recipe("Tomato Pulav");
        Recipe palakPaneer=new Recipe("Palak Paneer");
//Adding all to recipe Tomato Pulav
        tomatoPulav.addRecipeIngredients(tomatoPulavRecipeIngredient);
        tomatoPulav.addRecipeIngredients(tomatoPulavRecipeIngredient2);
        tomatoPulav.addRecipeIngredients(tomatoPulavRecipeIngredient3);
        tomatoPulav.addRecipeIngredients(tomatoPulavRecipeIngredient4);

        tomatoPulav.addRecipeInstruction(tomatoRecipeInstruction);
        palakPaneer.addRecipeInstruction(palakRecipeInstruction);

        tomatoPulav.addRecipeCategory(vegan);
        palakPaneer.addRecipeCategory(vegitarian);

        //PalakPaneer adding all to recipe
        palakPaneer.addRecipeIngredients(palakPaneerIngredient1);
        palakPaneer.addRecipeIngredients(palakPaneerIngredient2);
        //   palakPaneer.addRecipeIngredients(palakPaneerIngredient3);


        recipeRepository.save(tomatoPulav);
        recipeRepository.save(palakPaneer);
//Added on 21st October
        tomatoPulavRecipeIngredient.addRecipe(tomatoPulav);

    }


    @Test
    void findRecipeByRecipeName() {
        Recipe found=recipeRepository.findRecipeByRecipeName("Tomato Pulav");
        assertNotNull(found);
        assertEquals("Tomato Pulav",found.getRecipeName());

    }

    @Test
    void findRecipesByRecipeIngredientsIngredientIngredientName() {

        Collection<Recipe> found=recipeRepository.findRecipesByRecipeIngredientsIngredientIngredientName("rice");
        System.out.println("anusha "+found.size());
        found.forEach(recipe -> System.out.println("hi "+recipe));

        assertEquals(1,found.size());
        //  assertEquals(1, found.stream()
        //        .filter(recipe -> "Tomato Pulav".equalsIgnoreCase(recipe.getRecipeName())).count());

    }

    @Test
    void findRecipesByCategoriesCategory() {

        Collection<Recipe> found=recipeRepository.findRecipesByCategoriesCategory("vegan");
        assertEquals(1,found.size());
        assertNotNull(found.stream().anyMatch(recipe -> "Tomato pulav".equalsIgnoreCase(recipe.getRecipeName())));
    }

    @Test
    void findAll(){
        Collection<Recipe> resultFound = (Collection<Recipe>) recipeRepository.findAll();
        assertEquals(2, resultFound.size());

    }
}
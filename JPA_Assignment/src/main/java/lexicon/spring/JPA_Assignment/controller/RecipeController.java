package lexicon.spring.JPA_Assignment.controller;


import lexicon.spring.JPA_Assignment.data.*;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.*;
import lexicon.spring.JPA_Assignment.model.entity.*;
import lexicon.spring.JPA_Assignment.service.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
private final IngredientService ingredientService;
private final RecipeInstructionService recipeInstructionService;
private final RecipeIngredientService recipeIngredientService;
private final RecipeCategoryService recipeCategoryService;
private final RecipeService recipeService;
@Autowired
    public RecipeController(IngredientService ingredientService, RecipeInstructionService recipeInstructionService, RecipeIngredientService recipeIngredientService, RecipeCategoryService recipeCategoryService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeInstructionService = recipeInstructionService;
        this.recipeIngredientService = recipeIngredientService;
        this.recipeCategoryService = recipeCategoryService;
        this.recipeService = recipeService;
    }


    @PostMapping("/api/recipe/ingredient")
    public ResponseEntity<IngredientForm> createIngredients(@RequestBody IngredientForm ingredientForm){

        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.create(ingredientForm));

    }

    @GetMapping("/api/recipe/ingredient")
    public ResponseEntity<List<IngredientForm>> findAllIngredients(){

        return ResponseEntity.ok(ingredientService.findAll());
    }

    @GetMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<IngredientForm> findIngredientsById(@PathVariable("ingredientId") Integer ingredientId){
        return ResponseEntity.ok(ingredientService.findById(ingredientId));
    }

    @PutMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<Void> updateIngredient(@PathVariable("ingredientId") Integer ingredientId, @RequestBody IngredientForm ingredientForm){
            ingredientService.findById(ingredientId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @DeleteMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("ingredientId") Integer ingredientId){
            ingredientService.deleteById(ingredientId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Recipe Category controll methods

    @PostMapping("api/recipe/category")
    public ResponseEntity<RecipeCategoryForm> createRecipeCategory(@RequestBody RecipeCategoryForm recipeCategoryForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeCategoryService.create(recipeCategoryForm));
    }

    @GetMapping("/api/recipe/category")
    public ResponseEntity<List<RecipeCategoryForm>> findAllRecipeCategory(){
        return ResponseEntity.ok(recipeCategoryService.findAll());
    }

    @GetMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<RecipeCategoryForm> findRecipeCategoryById(@PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(recipeCategoryService.findById(categoryId));

    }


    @PutMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<Void> updateRecipeCategory(@PathVariable("categoryId") Integer categoryId, @RequestBody RecipeCategoryForm recipeCategoryForm){
           recipeCategoryService.update(recipeCategoryForm,categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Integer categoryId){
           recipeCategoryService.deleteById(categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


    //Api call for Recipe GET,PUT,DELETE

    @PostMapping("/api/recipe/recipe")
    public ResponseEntity<RecipeForm> createRecipe(@RequestBody RecipeForm recipeForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.create(recipeForm));
    }

    @GetMapping("/api/recipe/recipe")
    public ResponseEntity<List<RecipeForm>> findAllRecipes(){
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<RecipeForm> findRecipeById(@PathVariable("recipeId") Integer recipeId){
        return ResponseEntity.ok(recipeService.findById(recipeId));
    }

    @PutMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<Void> updateRecipeById(@PathVariable("recipeId") Integer recipeId, @RequestBody RecipeForm recipeForm){
         recipeService.update(recipeForm,recipeId);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("recipeId") Integer recipeId){
           recipeService.deleteById(recipeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    //Recipe Instruction Api calls GET, UPDATE,DELETE, POST

    @PostMapping("/api/recipe/recipeInstruction")
    public ResponseEntity<RecipeInstructionForm> createRecipe(@RequestBody RecipeInstructionForm recipeInstructionForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeInstructionService.create(recipeInstructionForm));
    }
    @GetMapping("/api/recipe/recipeInstruction")
    public ResponseEntity<List<RecipeInstructionForm>> findAllRecipeInstruction(){
        return ResponseEntity.ok(recipeInstructionService.findAll());
    }

    @GetMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<RecipeInstructionForm> findRecipeInstructionById(@PathVariable("recipeInstructionId") Integer recipeInstructionId){
        return ResponseEntity.ok(recipeInstructionService.findById(recipeInstructionId));
    }

    @PutMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<Void> updateRecipeInstruction(@PathVariable("recipeInstructionId") Integer recipeInstructionId, @RequestBody RecipeInstructionForm recipeInstructionForm){
        recipeInstructionService.update(recipeInstructionForm,recipeInstructionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<Void> deleteRecipeInstruction(@PathVariable("recipeInstructionId") Integer recipeInstructionId){
           recipeInstructionService.deleteById(recipeInstructionId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
// API calls for RecipeIngredient


    @PostMapping("/api/recipe/recipeIngredient")
    public ResponseEntity<RecipeIngredientForm> createRecipeIngredient(@RequestBody RecipeIngredientForm recipeIngredientForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeIngredientService.create(recipeIngredientForm));
    }
    @GetMapping("/api/recipe/recipeIngredient")
    public ResponseEntity<List<RecipeIngredientForm>> findAllRecipeIngredients(){
        return ResponseEntity.ok(recipeIngredientService.findAll());
    }

    @GetMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<RecipeIngredientForm> findRecipeIngredientById(@PathVariable("recipeIngredientId") Integer recipeIngredientId){
        return ResponseEntity.ok(recipeIngredientService.findById(recipeIngredientId));
    }

    @PutMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<Void> updateRecipeIngredient(@PathVariable("recipeIngredientId") Integer recipeIngredientId, @RequestBody RecipeIngredientForm recipeIngredientForm){
          recipeIngredientService.update(recipeIngredientForm,recipeIngredientId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<Void> deleteRecipeIngredient(@PathVariable("recipeIngredientId") Integer recipeIngredientId){
             recipeIngredientService.deleteById(recipeIngredientId);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

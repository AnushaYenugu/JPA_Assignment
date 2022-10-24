package lexicon.spring.JPA_Assignment.controller;


import lexicon.spring.JPA_Assignment.data.*;
import lexicon.spring.JPA_Assignment.exception.ResourceNotFoundException;
import lexicon.spring.JPA_Assignment.model.dto.*;
import lexicon.spring.JPA_Assignment.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
private final IngredientRepository ingredientRepository;
private final RecipeCategoryRepository recipeCategoryRepository;
private final RecipeIngredientRepository recipeIngredientRepository;
private final RecipeInstructionRepository recipeInstructionRepository;
private final RecipeRepository recipeRepository;
private final ModelMapper modelMapper;
    @Autowired
    public RecipeController(IngredientRepository ingredientRepository, RecipeCategoryRepository recipeCategoryRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeInstructionRepository recipeInstructionRepository, RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeInstructionRepository = recipeInstructionRepository;
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/recipe/ingredient")
    public ResponseEntity<IngredientForm> createIngredients(@RequestBody IngredientForm ingredientForm){

        Ingredient ingredient=modelMapper.map(ingredientForm,Ingredient.class);
        Ingredient ingredientSaved=ingredientRepository.save(ingredient);
        IngredientForm ingredientFormMapper=modelMapper.map(ingredientSaved, IngredientForm.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientFormMapper);

    }

    @GetMapping("/api/recipe/ingredient")
    public ResponseEntity<List<IngredientForm>> findAllIngredients(){
        Iterable<Ingredient> ingredientList=ingredientRepository.findAll();
        List<IngredientForm> ingredientFormList=modelMapper.map(ingredientList
                                                                ,new TypeToken<List<IngredientForm>>(){}.getType());

        return ResponseEntity.ok(ingredientFormList);
    }

    @GetMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<IngredientForm> findIngredientsById(@PathVariable("ingredientId") Integer ingredientId){
        if(ingredientId==null)throw new IllegalArgumentException("Ingredient Id is null");
        Ingredient foundById=ingredientRepository.findById(ingredientId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Id not found"));
        return ResponseEntity.ok(modelMapper.map(foundById, IngredientForm.class));
    }

    @PutMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<Void> updateIngredient(@PathVariable("ingredientId") Integer ingredientId, @RequestBody IngredientForm ingredientForm){

        if(ingredientId==null) throw new IllegalArgumentException("Ingredient Id is null");
        if(ingredientRepository.existsById(ingredientId)){
            // if(ingredientId==ingredientForm.getIngredientId()){
            Ingredient ingredient=modelMapper.map(ingredientForm,Ingredient.class);
            ingredientRepository.save(ingredient);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(418).build();
        }
    }
    @DeleteMapping("/api/recipe/ingredient/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("ingredientId") Integer ingredientId){
        if(ingredientId==null) throw new IllegalArgumentException("Ingredient Id is null");
        if(ingredientRepository.existsById(ingredientId)){
            ingredientRepository.deleteById(ingredientId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Recipe Category controll methods

    @PostMapping("api/recipe/category")
    public ResponseEntity<RecipeCategoryForm> createRecipeCategory(@RequestBody RecipeCategoryForm recipeCategoryForm){
        RecipeCategory recipeCategory=modelMapper.map(recipeCategoryForm,RecipeCategory.class);
        RecipeCategory savedRecipeCategory=recipeCategoryRepository.save(recipeCategory);
        RecipeCategoryForm recipeCategoryFormMapped=modelMapper.map(savedRecipeCategory,RecipeCategoryForm.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeCategoryFormMapped);
    }

    @GetMapping("/api/recipe/category")
    public ResponseEntity<List<RecipeCategoryForm>> findAllRecipeCategory(){
        Iterable<RecipeCategory> recipeCategoryList=recipeCategoryRepository.findAll();
        List<RecipeCategoryForm> recipeCategoryFormList=modelMapper.map(recipeCategoryList,
               new TypeToken<List<RecipeCategoryForm>>(){}.getType());
        return ResponseEntity.ok(recipeCategoryFormList);
    }

    @GetMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<RecipeCategoryForm> findRecipeCategoryById(@PathVariable("categoryId") Integer categoryId){
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");
        RecipeCategory foundByRecipeCategoryId=recipeCategoryRepository.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category Resource not Found"));
        return ResponseEntity.ok(modelMapper.map(foundByRecipeCategoryId,RecipeCategoryForm.class));

    }


    @PutMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<Void> updateRecipeCategory(@PathVariable("categoryId") Integer categoryId, @RequestBody RecipeCategoryForm recipeCategoryForm){
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");

        if(recipeCategoryRepository.existsById(categoryId)){
          //  if(categoryId==recipeCategoryForm.getCategoryId()){
            RecipeCategory recipeCategory=modelMapper.map(recipeCategoryForm,RecipeCategory.class);
            recipeCategoryRepository.save(recipeCategory);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(418).build();
        }
    }


    @DeleteMapping("/api/recipe/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        if(categoryId==null) throw new IllegalArgumentException("Category Id is null");
        if(recipeCategoryRepository.existsById(categoryId)){
            recipeCategoryRepository.deleteById(categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //Api call for Recipe GET,PUT,DELETE

    @PostMapping("/api/recipe/recipe")
    public ResponseEntity<RecipeForm> createRecipe(@RequestBody RecipeForm recipeForm){
        Recipe recipe=modelMapper.map(recipeForm,Recipe.class);
        System.out.println(recipeForm);
        Recipe savedRecipe=recipeRepository.save(recipe);
        RecipeForm recipeFormMapped=modelMapper.map(savedRecipe,RecipeForm.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeFormMapped);
    }

    @GetMapping("/api/recipe/recipe")
    public ResponseEntity<List<RecipeForm>> findAllRecipes(){
        Iterable<Recipe> recipeList=recipeRepository.findAll();
        List<RecipeForm> recipeFormList=modelMapper.map(recipeList
                                                        ,new TypeToken<List<RecipeForm>>(){}.getType());
        return ResponseEntity.ok(recipeFormList);
    }

    @GetMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<RecipeForm> findRecipeById(@PathVariable("recipeId") Integer recipeId){
        if(recipeId==null) throw new IllegalArgumentException("Recipe Id is null");
        Recipe foundByRecipeId=recipeRepository.findById(recipeId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Resource Not Found"));
        return ResponseEntity.ok(modelMapper.map(foundByRecipeId,RecipeForm.class));
    }

    @PutMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<Void> updateRecipeById(@PathVariable("recipeId") Integer recipeId, @RequestBody RecipeForm recipeForm){
        if(recipeId==null) throw new IllegalArgumentException("Recipe id is null");
        if(recipeRepository.existsById(recipeId)){
            Recipe recipe=modelMapper.map(recipeForm,Recipe.class);
            recipeRepository.save(recipe);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/api/recipe/recipe/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("recipeId") Integer recipeId){
        if(recipeId==null) throw new IllegalArgumentException("Recipe Id is null");
        if(recipeRepository.existsById(recipeId)){
            recipeRepository.deleteById(recipeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //Recipe Instruction Api calls GET, UPDATE,DELETE, POST

    @PostMapping("/api/recipe/recipeInstruction")
    public ResponseEntity<RecipeInstructionForm> createRecipe(@RequestBody RecipeInstructionForm recipeInstructionForm){
        RecipeInstruction recipeInstruction=modelMapper.map(recipeInstructionForm,RecipeInstruction.class);
        RecipeInstruction savedRecipeInstruction=recipeInstructionRepository.save(recipeInstruction);
        RecipeInstructionForm recipeInstructionFormMapped=modelMapper.map(savedRecipeInstruction,RecipeInstructionForm.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeInstructionFormMapped);
    }
    @GetMapping("/api/recipe/recipeInstruction")
    public ResponseEntity<List<RecipeInstructionForm>> findAllRecipeInstruction(){
        Iterable<RecipeInstruction> recipeInstructionList=recipeInstructionRepository.findAll();
        List<RecipeInstructionForm> recipeInstructionFormList=modelMapper.map(recipeInstructionList
                ,new TypeToken<List<RecipeInstructionForm>>(){}.getType());
        return ResponseEntity.ok(recipeInstructionFormList);
    }

    @GetMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<RecipeInstructionForm> findRecipeInstructionById(@PathVariable("recipeInstructionId") Integer recipeInstructionId){
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Id is null");
        RecipeInstruction foundByRecipeInstructionId=recipeInstructionRepository.findById(recipeInstructionId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Instruction Resource Not Found"));
        return ResponseEntity.ok(modelMapper.map(foundByRecipeInstructionId,RecipeInstructionForm.class));
    }

    @PutMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<Void> updateRecipeInstruction(@PathVariable("recipeInstructionId") Integer recipeInstructionId, @RequestBody RecipeInstructionForm recipeInstructionForm){
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Instruction id is null");
        if(recipeRepository.existsById(recipeInstructionId)){
            RecipeInstruction recipeInstruction=modelMapper.map(recipeInstructionForm,RecipeInstruction.class);
            recipeInstructionRepository.save(recipeInstruction);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/api/recipe/recipeInstruction/{recipeInstructionId}")
    public ResponseEntity<Void> deleteRecipeInstruction(@PathVariable("recipeInstructionId") Integer recipeInstructionId){
        if(recipeInstructionId==null) throw new IllegalArgumentException("Recipe Id is null");
        if(recipeInstructionRepository.existsById(recipeInstructionId)){
            recipeInstructionRepository.deleteById(recipeInstructionId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
// API calls for RecipeIngredient


    @PostMapping("/api/recipe/recipeIngredient")
    public ResponseEntity<RecipeIngredientForm> createRecipeIngredient(@RequestBody RecipeIngredientForm recipeIngredientForm){
        RecipeIngredient recipeIngredient=modelMapper.map(recipeIngredientForm, RecipeIngredient.class);
        RecipeIngredient savedRecipeIngredient=recipeIngredientRepository.save(recipeIngredient);
        RecipeIngredientForm recipeIngredientFormMapped=modelMapper.map(savedRecipeIngredient,RecipeIngredientForm.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeIngredientFormMapped);
    }
    @GetMapping("/api/recipe/recipeIngredient")
    public ResponseEntity<List<RecipeIngredientForm>> findAllRecipeIngredients(){
        Iterable<RecipeIngredient> recipeIngredientList=recipeIngredientRepository.findAll();
        List<RecipeIngredientForm> recipeIngredientFormList=modelMapper.map(recipeIngredientList
                ,new TypeToken<List<RecipeIngredientForm>>(){}.getType());
        return ResponseEntity.ok(recipeIngredientFormList);
    }

    @GetMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<RecipeIngredientForm> findRecipeIngredientById(@PathVariable("recipeIngredientId") Integer recipeIngredientId){
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Id is null");
        RecipeIngredient foundByRecipeIngredientId=recipeIngredientRepository.findById(recipeIngredientId).orElseThrow(
                ()->new ResourceNotFoundException("Recipe Ingredient Resource Not Found"));
        return ResponseEntity.ok(modelMapper.map(foundByRecipeIngredientId,RecipeIngredientForm.class));
    }

    @PutMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<Void> updateRecipeIngredient(@PathVariable("recipeIngredientId") Integer recipeIngredientId, @RequestBody RecipeIngredientForm recipeIngredientForm){
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Ingredient id is null");
        if(recipeIngredientRepository.existsById(recipeIngredientId)){
            RecipeIngredient recipeIngredient=modelMapper.map(recipeIngredientForm,RecipeIngredient.class);
            recipeIngredientRepository.save(recipeIngredient);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/api/recipe/recipeIngredient/{recipeIngredientId}")
    public ResponseEntity<Void> deleteRecipeIngredient(@PathVariable("recipeIngredientId") Integer recipeIngredientId){
        if(recipeIngredientId==null) throw new IllegalArgumentException("Recipe Ingredient Id is null");
        if(recipeIngredientRepository.existsById(recipeIngredientId)){
            recipeIngredientRepository.deleteById(recipeIngredientId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}

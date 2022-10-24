package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IngredientRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    IngredientRepository ingredientRepository;

    Ingredient testObject;

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

    @BeforeEach
    void setUp() {

List<Ingredient> listOfIngredients= ingredients().stream()
        .map(testEntityManager::persist).collect(Collectors.toList());



    }


    @Test
    void testFindIngredientByIngredientNameIgnoreCase() {
        Ingredient found= ingredientRepository.findIngredientByIngredientNameIgnoreCase("spinach");
        assertNotNull(found);
        assertEquals("spinach",found.getIngredientName());

    }

    @Test
    void testFindIngredientsByIngredientNameContainsIgnoreCase() {

        List<Ingredient> foundList=ingredientRepository.findIngredientsByIngredientNameContainsIgnoreCase("t");
        assertNotNull(foundList);
        assertEquals(2,foundList.size());

    }
}
package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.entity.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient,Integer> {

List<RecipeIngredient> findAll();

}

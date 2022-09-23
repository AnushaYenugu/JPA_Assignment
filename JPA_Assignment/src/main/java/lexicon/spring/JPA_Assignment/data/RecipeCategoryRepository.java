package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.RecipeCategory;
import org.springframework.data.repository.CrudRepository;

public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory,Integer> {
}

package lexicon.spring.JPA_Assignment.data;

import lexicon.spring.JPA_Assignment.model.entity.RecipeInstruction;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction,Integer> {

}

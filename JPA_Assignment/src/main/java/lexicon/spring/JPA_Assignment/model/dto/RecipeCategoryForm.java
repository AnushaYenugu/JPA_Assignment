package lexicon.spring.JPA_Assignment.model.dto;

import lexicon.spring.JPA_Assignment.model.entity.Recipe;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeCategoryForm {
   // private int categoryId;
    private String category;
  //  Collection<Recipe> recipe=new ArrayList<>();

}

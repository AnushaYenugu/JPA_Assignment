package lexicon.spring.JPA_Assignment.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String category;
    @ManyToMany(
            mappedBy = "categories",
            cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    Set<Recipe> recipe=new HashSet<>();

    public RecipeCategory() {
    }

    public RecipeCategory(String category) {
        this.category = category;
    }

    public RecipeCategory(String category, Set<Recipe> recipe) {
        this.category = category;
        this.recipe = recipe;
    }

    public RecipeCategory(int categoryId, String category, Set<Recipe> recipe) {
        this.categoryId = categoryId;
        this.category = category;
        this.recipe = recipe;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory that = (RecipeCategory) o;
        return categoryId == that.categoryId && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category);
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}

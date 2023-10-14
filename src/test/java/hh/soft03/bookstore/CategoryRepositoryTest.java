package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.Category;
import hh.soft03.bookstore.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        // Create a new category
        Category category = new Category();
        category.setName("Test Category");

        // Save the category using the repository
        categoryRepository.save(category);

        // Retrieve the saved category from the repository
        Category savedCategory = categoryRepository.findByName("Test Category");

        // Assert that the saved category is not null and has the expected attributes
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Test Category");
    }

    @Test
    public void testDeleteCategory() {
        // Create a new category and save it to the database
        Category category = new Category();
        category.setName("Test Category");
        categoryRepository.save(category);

        // Delete the category using the repository
        categoryRepository.delete(category);

        // Try to retrieve the deleted category from the repository
        Category deletedCategory = categoryRepository.findByName("Test Category");

        // Assert that the deleted category is null
        assertThat(deletedCategory).isNull();
    }
}


package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.Category;
import hh.soft03.bookstore.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void CreateNewCategory(){
        Category category = new Category("Science Fiction");
        categoryRepository.save(category);
    }
    @Test
    public void findByName(){
        Category category = categoryRepository.findByName("Comic");
        assertThat(category.getName()).isEqualTo("Comic");

    }

    @Test
    public void deleteByName(){
        Category category = categoryRepository.findByName("Comic");
        categoryRepository.delete(category);
    }

}

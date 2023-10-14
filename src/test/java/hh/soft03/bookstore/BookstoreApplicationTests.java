package hh.soft03.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.soft03.bookstore.web.BookstoreController;
import hh.soft03.bookstore.web.CategoryController;
import hh.soft03.bookstore.web.UserController;

@SpringBootTest
class BookstoreApplicationTests {


	    @Autowired
	    private BookstoreController bookstoreController;
	    @Autowired
	    private CategoryController categoryController;
	    @Autowired
	    private UserController userController;

	    @Test
	    public void contextLoads() throws Exception {
	        assertThat(bookstoreController).isNotNull();
	        assertThat(categoryController).isNotNull();
	        assertThat(userController).isNotNull();
	    }
}

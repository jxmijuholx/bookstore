package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;

import hh.soft03.bookstore.domain.CategoryRepository;
import hh.soft03.bookstore.domain.UserRepository;
import hh.soft03.bookstore.web.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void CreateNewBook(){
        Book book = new Book("Book 1", "Author 1", 2023, "ISBN123", 29.99, categoryRepository.findByName("Comic"));
        bookRepository.save(book);
    }

    @Test
     public void findByTitle() {
    	List<Book> books = bookRepository.findByTitle("Book 1");
    	assertThat(books.get(0).getAuthor()).isEqualTo("Author 1");
    }

    @Test
    public void deleteByTitle(){
        Book book = bookRepository.findByTitle("Book 1").get(0);
        bookRepository.delete(book);
    }
}

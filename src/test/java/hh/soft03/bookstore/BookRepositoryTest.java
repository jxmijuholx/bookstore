package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;

import hh.soft03.bookstore.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    

    @Test
    public void findByTitle() {

         Book book = new Book("Book 1", "Author 1", 2023, "ISBN123", 29.99, categoryRepository.findByName("Comic"));
         bookRepository.save(book);
    	List<Book> books = bookRepository.findByTitle("Book 1");
    	assertThat(books.get(0).getAuthor()).isEqualTo("Author 1");
    }
}

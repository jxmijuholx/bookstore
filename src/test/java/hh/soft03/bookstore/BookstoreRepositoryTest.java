package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookstoreRepositoryTest {


	    @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private TestEntityManager testEntityManager;

	    @Test
	    public void testCreateBook() {
	        // Create a new book
	        Book book = new Book();
	        book.setTitle("Test Book");
	        book.setAuthor("Test Author");
	        book.setIsbn("1234567890");

	        // Save the book using the repository
	        bookRepository.save(book);

	        // Use the TestEntityManager to retrieve the book from the database
	        Book savedBook = testEntityManager.find(Book.class, book.getId());

	        // Assert that the saved book is not null and has the expected attributes
	        assertThat(savedBook).isNotNull();
	        assertThat(savedBook.getTitle()).isEqualTo("Test Book");
	        assertThat(savedBook.getAuthor()).isEqualTo("Test Author");
	        assertThat(savedBook.getIsbn()).isEqualTo("1234567890");
	    }

	    @Test
	    public void testDeleteBook() {
	        // Create a new book and save it to the database
	        Book book = new Book();
	        book.setTitle("Test Book");
	        book.setAuthor("Test Author");
	        book.setIsbn("1234567890");
	        bookRepository.save(book);

	        // Delete the book using the repository
	        bookRepository.delete(book);

	        // Use the TestEntityManager to check if the book is deleted from the database
	        Book deletedBook = testEntityManager.find(Book.class, book.getId());

	        // Assert that the deleted book is null
	        assertThat(deletedBook).isNull();
	    }

	    @Test
	    public void testFindBookByTitle() {
	        // Create a new book and save it to the database
	        Book book = new Book();
	        book.setTitle("Test Book");
	        book.setAuthor("Test Author");
	        book.setIsbn("1234567890");
	        bookRepository.save(book);

	        // Use the repository to search for the book by title
	        Book foundBook =  bookRepository.findByTitle("Test Book");

	        // Assert that the found book is not null and has the expected attributes
	        assertThat(foundBook).isNotNull();
	        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
	        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
	        assertThat(foundBook.getIsbn()).isEqualTo("1234567890");
	    
	}

}

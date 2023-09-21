package hh.soft03.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;
import hh.soft03.bookstore.domain.Category;
import hh.soft03.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            Category category1 = new Category("Science Fiction");
            Category category2 = new Category("Comic");

            categoryRepository.save(category1);
            categoryRepository.save(category2);

            Book book1 = new Book("Book 1", "Author 1", 2023, "ISBN123", 29.99, category1);
            Book book2 = new Book("Book 2", "Author 2", 2023, "ISBN456", 19.99, category2);

            bookRepository.save(book1);
            bookRepository.save(book2);
        };
    }

    }


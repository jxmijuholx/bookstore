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
           
            Book book1 = new Book("Fjodor Dostojevski", "Muistelmia kuolleesta talosta", 2023, "ISBN123", 29.99);
            Book book2 = new Book("Fjodor Dostojevski", "Rikos ja rangaistus", 2023, "ISBN456", 19.99);

            bookRepository.save(book1);
            bookRepository.save(book2);

           
            Category scifi = new Category();
            scifi.setName("Science Fiction");
            categoryRepository.save(scifi);

            Category comic = new Category();
            comic.setName("Comic");
            categoryRepository.save(comic);
            
        };
    }
}

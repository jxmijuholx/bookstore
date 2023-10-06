package hh.soft03.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;
import hh.soft03.bookstore.domain.Category;
import hh.soft03.bookstore.domain.CategoryRepository;
import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registrationBean = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return (args) -> {
            Category category1 = new Category("Science Fiction");
            Category category2 = new Category("Comic");

            categoryRepository.save(category1);
            categoryRepository.save(category2);

            Book book1 = new Book("Book 1", "Author 1", 2023, "ISBN123", 29.99, category1);
            Book book2 = new Book("Book 2", "Author 2", 2023, "ISBN456", 19.99, category2);

            bookRepository.save(book1);
            bookRepository.save(book2);

            User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            User user2 = new User("admin", "$2a$10$Hl5q7w7wQNxGx.cKDF7tv..xvEQbHOG7rhwVC9X/ww3wF9RMig/ba", "ADMIN");
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}

package hh.soft03.bookstore;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
		 Book book1 = new Book("Jukka", "Juustola", 2023, "ISBN123", 29.99);
	     Book book2 = new Book("Jassi", "Jaatila", 2023, "ISBN456", 19.99);
	        
	     repository.save(book1);
	     repository.save(book2);
	};
	}
	}


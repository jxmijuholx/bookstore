package hh.soft03.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;



@Controller

public class BookstoreController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
		
	}
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		List<Book> books =  (List<Book>) bookRepository.findAll();
	     
		model.addAttribute("books", books);
		
		return "booklist";
	}
	
}

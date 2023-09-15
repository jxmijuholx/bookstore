package hh.soft03.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller

public class BookstoreController {
	
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
		
	}
	
}

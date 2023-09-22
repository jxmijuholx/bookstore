package hh.soft03.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.soft03.bookstore.domain.Book;
import hh.soft03.bookstore.domain.BookRepository;
import hh.soft03.bookstore.domain.CategoryRepository;

@Controller
public class BookstoreController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("newBook", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@ModelAttribute("newBook") Book newBook) {
        bookRepository.save(newBook);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
    	bookRepository.deleteById(id);
        return "redirect:/booklist";
    }  

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("categories", categoryRepository.findAll());
            return "editbook";
        } else {
            return "redirect:/booklist";
        }
    }

    @PostMapping("/editbook")
    public String saveEditedBook(@ModelAttribute Book editedBook) {
        bookRepository.save(editedBook);
        return "redirect:/booklist";
    }
}

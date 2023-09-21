package hh.soft03.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.soft03.bookstore.domain.Category;
import hh.soft03.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	// näytetään kategoriat webbisivulla
	@GetMapping("/categorylist")
	public String CategoryList(Model model) {
		
		List<Category> categories =  (List<Category>) categoryRepository.findAll();
		model.addAttribute("categories", categories);		
		return "categorylist";
	}
	
	//näytetään kategoria webbisivulla
	@GetMapping("/addcategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("newCategory", new Category());
        return "addcategory";
    }

		//lähetetään webbisivulle ja tietokantaan uusi kategoria
    @PostMapping("/addcategory")
    public String addCategory(@ModelAttribute("newCategory") Category newCategory) {
        categoryRepository.save(newCategory);
        return "redirect:/categorylist";
    }
    
    //poista kategoria
    @GetMapping("/delete/{categoryid}")
    public String deleteBook(@PathVariable Long categoryid) {
    	categoryRepository.deleteById(categoryid);
        return "redirect:/categorylist"; 
    }
	
	
}

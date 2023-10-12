package hh.soft03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.soft03.bookstore.domain.SignupForm;
import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            // Käyttäjä ei ole vielä kirjautunut sisään, ohjaa kirjautumissivulle
            return "login";
        } else {
            // Käyttäjä on jo kirjautunut sisään, ohjaa esimerkiksi etusivulle
            return "redirect:/booklist";
        }
    }

    @RequestMapping(value = "/signup")
    public String addStudent(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");

                // Tarkista, onko käyttäjä jo olemassa
                User existingUser = userRepository.findByUsername(signupForm.getUsername());
                if (existingUser != null) {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }

                userRepository.save(newUser);
                return "redirect:/login";
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
            }
        }
        return "signup";
    }
}

package hh.soft03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.soft03.bookstore.domain.SignupForm;
import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;
import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
                String rawPassword = signupForm.getPassword();
                // Use the injected PasswordEncoder to encode the password
                String encodedPassword = passwordEncoder.encode(rawPassword);

             // Create a new user
                User newUser = new User();
                newUser.setPassword(encodedPassword); // Set the encoded password
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");


                // Check if the username already exists
                User existingUser = userRepository.findByUsername(signupForm.getUsername());
                if (existingUser != null) {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }

                // Ensure that the 'password' field is set
                newUser.setPassword(encodedPassword);

                userRepository.save(newUser);
                return "redirect:/login";
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
            }
        }
        return "signup";
    }
}


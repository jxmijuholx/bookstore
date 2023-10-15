package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import BCryptPasswordEncoder

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder

    @Test
    public void testCreateUser() {
        // Create a new user
        User user = new User();
        user.setUsername("testuser");

        // Set the password for the user
        String plainPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        // Set the user's role
        user.setRole("USER");

        // Save the user using the repository
        userRepository.save(user);

        // Retrieve the saved user from the repository
        User savedUser = userRepository.findByUsername("testuser");

        // Assert that the saved user is not null and has the expected attributes
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");

        // Add an assertion for the password
        assertThat(savedUser.getPassword()).isNotNull();
        assertThat(passwordEncoder.matches(plainPassword, savedUser.getPassword())).isTrue(); // Validate the hashed password

        // Add an assertion for the role
        assertThat(savedUser.getRole()).isEqualTo("USER"); // Check that the role is set as "USER"
    }
}

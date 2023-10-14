package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // Create a new user
    	User user = new User();
        user.setUsername("testuser");

        // Save the user using the repository
        userRepository.save(user);

        // Retrieve the saved user from the repository
        User savedUser = userRepository.findByUsername("testuser");

        // Assert that the saved user is not null and has the expected attributes
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testDeleteUser() {
        // Create a new user and save it to the database
        User user = new User();
        user.setUsername("testuser");
        userRepository.save(user);

        // Delete the user using the repository
        userRepository.delete(user);

        // Try to retrieve the deleted user from the repository
        User deletedUser = userRepository.findByUsername("testuser");

        // Assert that the deleted user is null
        assertThat(deletedUser).isNull();
    }
}

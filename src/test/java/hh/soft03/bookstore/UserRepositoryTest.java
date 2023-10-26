package hh.soft03.bookstore;

import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void CreateNewUser() {
        User user1 = new User("heikki", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
        User user2 = new User("jami", "$2a$10$Hl5q7w7wQNxGx.cKDF7tv..xvEQbHOG7rhwVC9X/ww3wF9RMig/ba", "ADMIN");

        userRepository.save(user1);
        userRepository.save(user2);

        User savedUser1 = userRepository.findByUsername("heikki");
        User savedUser2 = userRepository.findByUsername("jami");

        assertThat(savedUser1.getUsername()).isEqualTo("heikki");
        assertThat(savedUser1.getRole()).isEqualTo("USER");
        assertThat(savedUser2.getUsername()).isEqualTo("jami");
        assertThat(savedUser2.getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void findByUsername() {

        User user = userRepository.findByUsername("user");
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getRole()).isEqualTo("USER");
    }

    @Test
    public void deleteByUsername() {

        userRepository.deleteByUsername("user");
        User deletedUser = userRepository.findByUsername("user");
        assertThat(deletedUser).isNull();
    }
}

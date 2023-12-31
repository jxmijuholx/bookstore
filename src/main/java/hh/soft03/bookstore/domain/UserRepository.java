package hh.soft03.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	void deleteByUsername(String user);
}

package hh.soft03.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository	
public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String string);
	
}

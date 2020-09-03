package pl.akademiakodu.ksiazki.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.ksiazki.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}

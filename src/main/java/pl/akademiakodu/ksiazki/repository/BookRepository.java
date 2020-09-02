package pl.akademiakodu.ksiazki.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.ksiazki.model.Book;

public interface BookRepository  extends CrudRepository<Book, Integer> {
}

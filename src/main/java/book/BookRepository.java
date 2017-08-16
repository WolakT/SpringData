package book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RENT on 2017-08-16.
 */
public interface BookRepository extends CrudRepository<Book,Long> {
    List<Book> findByTitle (String title);
}

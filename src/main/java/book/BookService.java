package book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by RENT on 2017-08-16.
 */

@Service
public class BookService {


    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book create(Book book){
        return  bookRepository.save(book);
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book update(Book book) {
        return bookRepository.save(book);

    }

    public Book findOne(Long id){
        return bookRepository.findOne(id);
    }

    public void delete(Long id){
        bookRepository.delete(id);
    }
}

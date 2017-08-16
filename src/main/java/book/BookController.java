package book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by RENT on 2017-08-16.
 */

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/book/add")
    public ResponseEntity<Long> create(@RequestBody Book book){
        Book book1 = bookService.create(book);
        return new ResponseEntity<>(book1.getId(), HttpStatus.CREATED);
    }

    @RequestMapping("/book/findAll")
    public Iterable<Book> findAll(){
        return bookService.getAll();
    }

    @RequestMapping("/book/update")
    public ResponseEntity<Long> update(@RequestParam(value = "id") Long id, @RequestBody Book book){
        Book book1 = new Book(book.getTitle(),book.getAuthor(),book.getIsbn(),book.getCategory());
        book1.setId(id);
        Book updatedBook = bookService.update(book1);
        return new ResponseEntity<>(updatedBook.getId(), HttpStatus.ACCEPTED);
    }

    @RequestMapping("/book/delete")
    public ResponseEntity<Long> delete(@RequestParam(value="id")Long id){
        bookService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

}

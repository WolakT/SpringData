package book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RENT on 2017-08-16.
 */

@RestController
public class BookController {


    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody Book book){
        Book book1 = bookService.create(book);
        return new ResponseEntity<>(book1.getId(), HttpStatus.CREATED);
    }

    @RequestMapping("/book/findAll")
    public Iterable<Book> findAll(){
        return bookService.getAll();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public ResponseEntity<Long> update(@PathVariable("id") Long id, @RequestBody Book book){
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

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> findOne(@PathVariable("id") Long id){
        Book book = bookService.findOne(id);
        String stringBook = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            stringBook = objectMapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
    }

}

package book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by RENT on 2017-08-17.
 */
public class BookControllerTest {
    @Mock
    private BookService bookService;

    private BookController bookController;

    @Before
    public void setUp(){
        initMocks(this);
        bookController = new BookController(bookService);
        Book exampleBook = createExampleBook();
    }

    @Test
    public void shouldReturnBookById() throws JsonProcessingException {
        Book persistedBook = createPersistedBook();
        persistedBook.setCategory("history");
        Mockito.when(bookService.findOne(persistedBook.getId())).thenReturn(persistedBook);
        ResponseEntity<Book> responseEntity = bookController.findOne(persistedBook.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResult = objectMapper.writeValueAsString(persistedBook);
        Mockito.verify(bookService, Mockito.times(1)).findOne(persistedBook.getId());
        Assert.assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
        Assert.assertEquals(expectedResult, responseEntity.getBody());
    }
    private Book createExampleBook(){
        Book book = new Book();
        book.setAuthor("Adam Mickiewicz");
        book.setTitle("Pan Tadeusz");
        book.setIsbn("234-245-3532-23445");
        book.setCategory("history");
        return book;

    }
    private Book createPersistedBook(){
        Book book = new Book();
        book.setId(1L);
        return book;
    }

}
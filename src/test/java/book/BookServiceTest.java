package book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by RENT on 2017-08-17.
 */


public class BookServiceTest {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book exampleBook;

    @Before
    public void setUp(){
        initMocks(this);
        bookService = new BookService(bookRepository);
        exampleBook = createExampleBook();

    }

    @Test
    public void shouldAddBookToDatabase() {
        Long expectedID = 1L;
        Book persistedBook = createPersistedBook();


        Mockito.when(bookRepository.save(exampleBook)).thenReturn(persistedBook);

        Book resultBook = bookService.create(exampleBook);
        Assert.assertEquals(expectedID,resultBook.getId());
        Mockito.verify(bookRepository , Mockito.times(1)).save(exampleBook);
    }

    @Test
    public void shouldUpdatePersistedBook() {
        Book persistedBook = createPersistedBook();
        Book updatedBook = createUpdatedBook();
        Mockito.when(bookRepository.save(persistedBook)).thenReturn(updatedBook);
        Book resultBook = bookService.update(persistedBook);

        Assert.assertEquals(updatedBook.getCategory(), resultBook.getCategory());

        Mockito.verify(bookRepository, Mockito.times(1)).save(persistedBook);

    }

    @Test
    public void shouldGetExampleBookById(){
        Book persistedBook = createPersistedBook();
        Mockito.when(bookRepository.findOne(persistedBook.getId())).thenReturn(persistedBook);
        Book resultBook = bookService.findOne(persistedBook.getId());

        Mockito.verify(bookRepository, Mockito.times(1)).findOne(persistedBook.getId());
        Assert.assertEquals(persistedBook.getId(),resultBook.getId());
    }

    @Test
    public void shouldDeleteExampleBook(){
        Book persistedBook = createPersistedBook();
        bookService.delete(persistedBook.getId());
        Mockito.verify(bookRepository, Mockito.times(1)).delete(persistedBook.getId());
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

    private Book createUpdatedBook(){
        Book book = new Book();
        book.setCategory("updated category");
        return book;
    }

}
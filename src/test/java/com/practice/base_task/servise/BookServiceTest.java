package com.practice.base_task.servise;

import com.practice.base_task.BaseTaskApplication;
import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.dto.BookshelfDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.utils.DataMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = BaseTaskApplication.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private DataMapper dataMapper;
    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void findAllTest() {
        List<BookDTO> bookDTOS = bookService.findAll();
        Assertions.assertNotNull(bookDTOS);
    }

    @Test
    public void findAllTestByShelf() {
        List<BookDTO> bookDTOS = bookService.findAllByBookshelfId(1L);
        Assertions.assertNotNull(bookDTOS);
    }

    @Test
    public void findByIdTest() throws SQLException {

        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        bookshelfDTO.setShelfId(1L);
        bookshelfDTO.setShelfName("sss");
        bookshelfDTO.setLocation("yyy");

        BookDTO bookDTO = new BookDTO();

        Query genId = entityManager.createNativeQuery("SELECT nextval('book_seq') AS generated_id");
        Long generatedId = (long) genId.getSingleResult();

        bookDTO.setBookId(generatedId);
        bookDTO.setFirstName("John");
        bookDTO.setLastName("Doe");
        bookDTO.setBookName("Sample Book");
        bookDTO.setBookCount(1);
        bookDTO.setBookshelf(bookshelfDTO);

        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);

        Assertions.assertNotNull(bookService.findByIdWithLeftJoin(savedBook.getBookId()));

        bookService.deleteBook(savedBook.getBookId());

    }

    @Test
    public void createBookTest() throws SQLException {
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        bookshelfDTO.setShelfId(1L);
        bookshelfDTO.setShelfName("sss");
        bookshelfDTO.setLocation("yyy");

        BookDTO bookDTO = new BookDTO();

        bookDTO.setBookId(1L);
        bookDTO.setFirstName("John");
        bookDTO.setLastName("Doe");
        bookDTO.setBookName("Sample Book");
        bookDTO.setBookCount(1);
        bookDTO.setBookshelf(bookshelfDTO);

        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);

        // Проверяем, что созданная книга имеет ожидаемые параметры

        Assertions.assertEquals(bookDTO.getFirstName(), createdBookDTO.getFirstName());
        Assertions.assertEquals(bookDTO.getLastName(), createdBookDTO.getLastName());
        Assertions.assertEquals(bookDTO.getBookName(), createdBookDTO.getBookName());
        Assertions.assertEquals(bookDTO.getBookCount(), createdBookDTO.getBookCount());

        bookService.deleteBook(savedBook.getBookId());

    }

    @Test
    public void updateBookTest() throws SQLException {
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        bookshelfDTO.setShelfId(1L);
        bookshelfDTO.setShelfName("sss");
        bookshelfDTO.setLocation("yyy");

        BookDTO savedBookDTO = new BookDTO();

        savedBookDTO.setBookId(1L);
        savedBookDTO.setFirstName("John");
        savedBookDTO.setLastName("Doe");
        savedBookDTO.setBookName("Sample Book");
        savedBookDTO.setBookCount(1);
        savedBookDTO.setBookshelf(bookshelfDTO);

        BookDTO savedBookDto = bookService.createBook(savedBookDTO);

        BookDTO updateBookDTO = new BookDTO();
        updateBookDTO.setBookId(savedBookDto.getBookId());
        updateBookDTO.setFirstName("JohnUpdate");
        updateBookDTO.setLastName("DoeUpdate");
        updateBookDTO.setBookName("Sample Book Update");
        updateBookDTO.setBookCount(2);
        updateBookDTO.setBookshelf(bookshelfDTO);

        Book updateBook = dataMapper.convertBookDtoToEntity(bookService.updateBook(updateBookDTO));

        // Проверяем, что измененная книга имеет ожидаемые параметры
        Assertions.assertEquals(updateBook.getBookId(), updateBookDTO.getBookId());
        Assertions.assertEquals(updateBook.getFirstName(), updateBookDTO.getFirstName());
        Assertions.assertEquals(updateBook.getLastName(), updateBookDTO.getLastName());
        Assertions.assertEquals(updateBook.getBookName(), updateBookDTO.getBookName());
        Assertions.assertEquals(updateBook.getBookCount(), updateBookDTO.getBookCount());

        bookService.deleteBook(updateBook.getBookId());

    }

    @Test
    public void deleteBookTest() throws SQLException {
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        bookshelfDTO.setShelfId(1L);
        bookshelfDTO.setShelfName("sss");
        bookshelfDTO.setLocation("yyy");

        BookDTO bookDTO = new BookDTO();

        Query genId = entityManager.createNativeQuery("SELECT nextval('book_seq') AS generated_id");
        Long generatedId = (long) genId.getSingleResult();

        bookDTO.setBookId(generatedId);
        bookDTO.setFirstName("John");
        bookDTO.setLastName("Doe");
        bookDTO.setBookName("Sample Book");
        bookDTO.setBookCount(1);
        bookDTO.setBookshelf(bookshelfDTO);

        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);

        Assertions.assertNotNull(bookService.findByIdWithLeftJoin(savedBook.getBookId()));

        bookService.deleteBook(savedBook.getBookId());

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            bookService.findByIdWithLeftJoin(generatedId);
        });
    }

}

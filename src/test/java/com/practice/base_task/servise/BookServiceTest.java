//package com.practice.base_task.servise;
//
//import com.practice.base_task.BaseTaskApplication;
//import com.practice.base_task.dto.BookDTO;
//import com.practice.base_task.model.Book;
//import com.practice.base_task.utils.DataMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest(classes = BaseTaskApplication.class)
//class BookServiceTest {
//
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private DataMapper dataMapper;
//
//
//    @Test
//    public void findAllTest() {
//        List<Book> books = bookService.findAll();
//        Assertions.assertNotNull(books);
//    }
//
//    @Test
//    public void findAllTestByShelf() {
//        List<Book> books = bookService.findAllByBookshelfId(1L);
//        Assertions.assertNotNull(books);
//    }
//
//    @Test
//    public void findByIdTest() {
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setFirstName("John");
//        bookDTO.setLastName("Doe");
//        bookDTO.setBookName("Sample Book");
//        bookDTO.setBookCount(1);
//
//        BookDTO createdBookDTO = bookService.createBook(bookDTO);
//        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);
//
//        Assertions.assertNotNull(bookService.findByIdWithLeftJoin(savedBook.getBookId()));
//
//        bookService.deleteBook(savedBook.getBookId());
//
//    }
//
//    @Test
//    public void createOrUpdateBookTest() {
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setFirstName("John");
//        bookDTO.setLastName("Doe");
//        bookDTO.setBookName("Sample Book");
//        bookDTO.setBookCount(1);
//
//        BookDTO createdBookDTO = bookService.createBook(bookDTO);
//        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);
//
//
//
//        // Проверяем, что созданная книга имеет ожидаемые параметры
//        Assertions.assertEquals(savedBook.getFirstName(), createdBookDTO.getFirstName());
//        Assertions.assertEquals(savedBook.getLastName(), createdBookDTO.getLastName());
//        Assertions.assertEquals(savedBook.getBookName(), createdBookDTO.getBookName());
//        Assertions.assertEquals(savedBook.getBookCount(), createdBookDTO.getBookCount());
//
//        bookService.deleteBook(savedBook.getBookId());
//
//    }
//
//    @Test
//    public void deleteBookTest() {
//
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setFirstName("John");
//        bookDTO.setLastName("Doe");
//        bookDTO.setBookName("Sample Book");
//        bookDTO.setBookCount(1);
//
//        BookDTO createdBookDTO = bookService.createBook(bookDTO);
//        Book savedBook = dataMapper.convertBookDtoToEntity(createdBookDTO);
//
//        Assertions.assertNotNull(bookService.findByIdWithLeftJoin(savedBook.getBookId()));
//
//        bookService.deleteBook(savedBook.getBookId());
//
//        Book deletedBook = bookService.findByIdWithLeftJoin(savedBook.getBookId());
//
//
//        Assertions.assertThrows(NullPointerException.class, () -> {
//            bookService.findByIdWithLeftJoin(deletedBook.getBookId());
//        });
//    }
//
//}

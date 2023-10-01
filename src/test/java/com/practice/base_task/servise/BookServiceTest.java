//package com.practice.base_task.servise;
//
//import com.practice.base_task.BaseTaskApplication;
//import com.practice.base_task.dto.BookDTO;
//import com.practice.base_task.model.Book;
//import com.practice.base_task.repository.BookRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest(classes = BaseTaskApplication.class)
//class BookServiceTest {
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private BookRepository bookRepository;
//    @Autowired
//    private BookService bookService;
//
//
//    @Test
//    public void findAllTest() {
//        List<Book> books = bookService.findAll();
//        Assertions.assertNotNull(books);
//    }
//
//    @Test
//    public void findByIdTest() {
//        //создать -> проверить по id -> удалить?
//        Assertions.assertNotNull(bookService.findById(1L));
//    }

//    @Test
//    public void createOrUpdateBookTest() {
//
//        bookRepository = mock(BookRepository.class);
//        modelMapper = new ModelMapper();
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setFirstName("John");
//        bookDTO.setLastName("Doe");
//        bookDTO.setBookName("Test Book");
//        bookDTO.setBookCount(5);
//
//        Book book = new Book();
//        book.setFirstName("John");
//        book.setLastName("Doe");
//        book.setBookName("Test Book");
//        book.setBookCount(5);
//
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//        BookDTO savedBookDTO = bookService.createOrUpdateBook(bookDTO);
//
//        verify(bookRepository, times(1)).save(any(Book.class));
//
//        Assertions.assertEquals(savedBookDTO.getFirstName(), book.getFirstName());
//        Assertions.assertEquals(savedBookDTO.getLastName(), book.getLastName());
//        Assertions.assertEquals(savedBookDTO.getBookName(), book.getBookName());
//        Assertions.assertEquals(savedBookDTO.getBookCount(), book.getBookCount());
//    }

//    @Test
//    public void deleteBookTest() {
          //создать книгу, проверить, что она есть, удалить, проверить, что ее нет
//        Assertions.assertNotNull(book);
//        bookService.deleteBook(book.getBookId());
//
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            bookService.findById(book.getBookId());
//        });
//
////    }
//
//}

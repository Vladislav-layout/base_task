package com.practice.base_task.controller;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.repository.BookRepository;
import com.practice.base_task.servise.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@Api(description = "Библиотека")
public class BookСontroller {


    private final BookService bookService;

    @Autowired
    public BookСontroller(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @ApiOperation(value = "Список книг")
    public ResponseEntity<List<Book>> findAll () {
        List <Book> books = bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    @ApiOperation(value = "Книга")
    public ResponseEntity<BookDTO> getBookById(@PathVariable ("id") Long id)  {
            Book book = bookService.findById(id);
            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            BookDTO bookDTO = bookService.convertEntityToBookDto(book);
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/shelf/{id}")
    @ApiOperation(value = "Список книг в шкафу")
    public ResponseEntity<List<Book>> getBookByShelf(@PathVariable Long id)  {
       List <Book> books = bookService.findAllByBookshelfId(id);
       if (books.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(books, HttpStatus.OK);
    }


//    @PostMapping("/book/save")
//    @ApiOperation(value = "Создать книгу")
//    public ResponseEntity<Book> saveBook(@RequestBody BookDTO dto) {
//        return mappingResponseBook(bookService.saveBook(dto));
//    }
//
//    @PutMapping("/book/update")
//    @ApiOperation(value = "Изменить книгу")
//    public ResponseEntity<Book> updateBook(@RequestBody Book book){
//        return mappingResponseBook(bookService.updateBook(book));
//    }

    @DeleteMapping("/book/{id}")
    @ApiOperation(value = "Удалить книгу")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
            Book existingBook = bookService.findById(id);
            if (existingBook == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/book/createOrUpdate")
    @ApiOperation(value = "Создать/Изменить книгу")
    public ResponseEntity<BookDTO> createOrUpdateBook(@RequestBody BookDTO bookDTO) {
        BookDTO responseDto = bookService.createOrUpdateBook(bookDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}

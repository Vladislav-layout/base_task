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
    private final BookRepository bookRepository;

    @Autowired
    public BookСontroller(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    @ApiOperation(value = "Список книг")
    public ResponseEntity<List<Book>> findAll () {
        return mappingResponseListBook(bookService.findAll());
    }

    @GetMapping("/book/{id}")
    @ApiOperation(value = "Книга")
    public ResponseEntity<Book> getBook(@PathVariable ("id") Long id)  {
            return mappingResponseBook(bookService.findById(id));
    }

    @GetMapping("/shelf/{id}")
    @ApiOperation(value = "Список книг в шкафу")
    public ResponseEntity<List<Book>> getBookByShelf(@PathVariable Long id)  {
        return mappingResponseListBook(bookService.findAllByBookshelfId(id));
    }


    @PostMapping("/book/save")
    @ApiOperation(value = "Создать книгу")
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO dto) {
        return mappingResponseBook(bookService.saveBook(dto));
    }

    @PutMapping("/book/update")
    @ApiOperation(value = "Изменить книгу")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return mappingResponseBook(bookService.updateBook(book));
    }

    @DeleteMapping("/book/{id}")
    @ApiOperation(value = "Удалить книгу")
    public HttpStatus deleteBook(@PathVariable Long id){
            bookService.deleteBook(id);
            return HttpStatus.OK;
    }

    private ResponseEntity<Book> mappingResponseBook(Book book) {
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    private ResponseEntity<List<Book>> mappingResponseListBook(List<Book> books) {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}

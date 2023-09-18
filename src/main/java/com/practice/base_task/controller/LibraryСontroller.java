package com.practice.base_task.controller;

import com.practice.base_task.model.Book;
import com.practice.base_task.repository.LibraryRepository;
import com.practice.base_task.servise.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@Api(description = "Библиотека")
public class LibraryСontroller {


    private final BookService bookService;
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryСontroller(BookService bookService,
                             LibraryRepository libraryRepository) {
        this.bookService = bookService;
        this.libraryRepository = libraryRepository;
    }

    @GetMapping("/books")
    @ApiOperation(value = "Список книг")
    public List<Book> findAll () {
        List<Book> book = bookService.findAll();
        return book;
    }

    @GetMapping("/book/{id}")
    @ApiOperation(value = "Книга")
    public Optional<Book> getBook(@PathVariable ("id") Long id)  {
        return bookService.findById(id);
    }

    @PostMapping("book/save")
    @ApiOperation(value = "Создать книгу")
    public Optional<Book> saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return bookService.findById(book.getBookId());
    }

    @PutMapping("/book/update")
    @ApiOperation(value = "Изменить книгу")
    public Optional<Book> updateBook(@RequestBody Book book){
        bookService.updateBook(book);
        return bookService.findById(book.getBookId());
    }

    @DeleteMapping("/book/{id}")
    @ApiOperation(value = "Удалить книгу")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}

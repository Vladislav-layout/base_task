package com.practice.base_task.controller;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.servise.BookService;
import com.practice.base_task.utils.DataMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("library")
@AllArgsConstructor
@Tag(name = "Библиотека", description = "Методы для работы с книгами")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;
    private final DataMapper dataMapper;

    @GetMapping("books")
    @Operation(summary = "Список книг")
    public ResponseEntity<List<BookDTO>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/shelf/{id}")
    @Operation(summary = "Список книг в шкафу")
    public ResponseEntity<List<BookDTO>> getBookByShelf(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findAllByBookshelfId(id), HttpStatus.OK);
    }

    @PutMapping("book/create")
    @Operation(summary = "Создать книгу")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) throws SQLException {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PostMapping("book/update")
    @Operation(summary = "Изменить книгу")
    public ResponseEntity<BookDTO> UpdateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.CREATED);
    }


    @GetMapping("book/{id}")
    @Operation(summary = "Книга")
    public ResponseEntity<BookDTO> findById (@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.findByIdWithLeftJoin(id), HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    @Operation(summary = "Удалить книгу")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.practice.base_task.controller;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.servise.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("library")
@AllArgsConstructor
@Tag(name = "Библиотека", description = "Методы для работы с книгами")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @GetMapping("books")
    @Operation(summary = "Список книг")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> books = bookService.findAll();
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : books) {
            bookDTOS.add(modelMapper.map(book,BookDTO.class));
        }

        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @PostMapping("book/createOrUpdate")
    @Operation(summary = "Создать/Изменить книгу")
    public ResponseEntity<BookDTO> createOrUpdateBook(@RequestBody BookDTO bookDTO) {
        BookDTO responseDto = bookService.createOrUpdateBook(bookDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping("book/{id}")
    @Operation(summary = "Книга")
    public ResponseEntity<BookDTO> findById (@PathVariable("id") Long id) {
        Book book = bookService.findByIdWithLeftJoin(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookDTO bookDTO = bookService.convertEntityToBookDto(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }


//    @GetMapping("/shelf/{id}")
//    @Operation(summary = "Список книг в шкафу")
//    public ResponseEntity<List<Book>> getBookByShelf(@PathVariable Long id) {
//        List<Book> books = bookService.findAllByBookshelfId(id);
//        if (books == null || books.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

    @DeleteMapping("book/{id}")
    @Operation(summary = "Удалить книгу")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        Book existingBook = bookService.findByIdWithLeftJoin(id);
        if (existingBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.practice.base_task.controller;


import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.dto.BookshelfDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.model.Bookshelf;
import com.practice.base_task.servise.ShelvesService;
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
@RequestMapping("/shelf")
@AllArgsConstructor
@Tag(name = "Шкафы")
public class ShelfController {

    private final ShelvesService shelvesService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(description = "Список шкафов")
    public ResponseEntity<List<BookshelfDTO>> findAllBookshelves() {
        List<Bookshelf> bookshelves = shelvesService.findAllShelves();
        if (bookshelves == null || bookshelves.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BookshelfDTO> bookshelfDTOS = new ArrayList<>();
        for (Bookshelf bookshelf : bookshelves) {
            bookshelfDTOS.add(modelMapper.map(bookshelf,BookshelfDTO.class));
        }

        return new ResponseEntity<>(bookshelfDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Шкаф")
    public ResponseEntity<BookshelfDTO> getBookshelf(@PathVariable("id") Long id) {
        Bookshelf bookshelf = shelvesService.findShelfById(id);
        if (bookshelf == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookshelfDTO bookshelfDTO = shelvesService.convertEntityToBookshelfDTO(bookshelf);
        return new ResponseEntity<>(bookshelfDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    @Operation(description = "Создать шкаф")
    public ResponseEntity<BookshelfDTO> createOrUpdateBookshelf(@RequestBody BookshelfDTO bookshelfDTO) {
        BookshelfDTO responseDto = shelvesService.createOrUpdateBookshelf(bookshelfDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить шкаф")
    public ResponseEntity<Void> deleteBookshelf(@PathVariable Long id) {
        Bookshelf existingBookshelf = shelvesService.findShelfById(id);
        if (existingBookshelf == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        shelvesService.deleteBookshelf(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<Bookshelf> mappingResponseBookshelf(Bookshelf bookshelf) {
        return new ResponseEntity<>(bookshelf, HttpStatus.OK);
    }

    private ResponseEntity<List<Bookshelf>> mappingResponseListBookshelf(List<Bookshelf> bookshelves) {
        return new ResponseEntity<>(bookshelves, HttpStatus.OK);
    }
}

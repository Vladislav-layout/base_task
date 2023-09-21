package com.practice.base_task.controller;


import com.practice.base_task.model.Book;
import com.practice.base_task.model.Bookshelf;
import com.practice.base_task.repository.ShelvesRepository;
import com.practice.base_task.servise.ShelvesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
@Api(description = "Шкафы")
public class ShelfController {

    private final ShelvesRepository shelvesRepository;
    private final ShelvesService shelvesService;

    @Autowired
    public ShelfController(ShelvesRepository shelvesRepository, ShelvesService shelvesService) {
        this.shelvesRepository = shelvesRepository;
        this.shelvesService = shelvesService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Список шкафов")
    public ResponseEntity<List<Bookshelf>> findAll () {
        return mappingResponseListBookshelf(shelvesService.findAllShelves());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Шкаф")
    public ResponseEntity<Bookshelf> getBook(@PathVariable("id") Long id)  {
        return mappingResponseBookshelf(shelvesService.findShelfById(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Создать шкаф")
    public ResponseEntity<Bookshelf> saveBook(@RequestBody Bookshelf bookshelf) {
        return mappingResponseBookshelf(shelvesService.saveBookshelf(bookshelf));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Изменить шкаф")
    public ResponseEntity<Bookshelf> updateBookshelf(@RequestBody Bookshelf bookshelf){
        return mappingResponseBookshelf(shelvesService.updateBookshelf(bookshelf));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить шкаф")
    public HttpStatus deleteBook(@PathVariable Long id){
        shelvesService.deleteBookshelf(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Bookshelf> mappingResponseBookshelf(Bookshelf bookshelf) {
        return new ResponseEntity<>(bookshelf, HttpStatus.OK);
    }
    private ResponseEntity<List<Bookshelf>> mappingResponseListBookshelf(List<Bookshelf> bookshelves) {
        return new ResponseEntity<>(bookshelves, HttpStatus.OK);
    }
}

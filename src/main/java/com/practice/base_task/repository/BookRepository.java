package com.practice.base_task.repository;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;


public interface BookRepository {

    List<Book> findAllByBookshelfId(@PathVariable("shelfId") Long shelfId);
    Book findByIdWithLeftJoin(@PathVariable("id") Long id);
    List<Book> findAll();
    void deleteById(@PathVariable("id") Long id);
    Book save(BookDTO bookDTO) throws SQLException;
    Book update(BookDTO bookDTO);
}

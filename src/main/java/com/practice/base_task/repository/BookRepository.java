package com.practice.base_task.repository;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface BookRepository /*extends JpaRepository<Book, Long>*/ {
//    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookshelf WHERE b.bookshelf.id = :shelfId")
//    List<Book> findAllByBookshelfId(@PathVariable("shelfId") Long shelfId);
//    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookshelf WHERE b.bookId = :id")
//    Book findByIdWithLeftJoin(@PathVariable("id") Long id);


    List<Book> findAllByBookshelfId(@PathVariable("shelfId") Long shelfId);
    Book findByIdWithLeftJoin(@PathVariable("id") Long id);
    List<Book> findAll();
    void deleteById(@PathVariable("id") Long id);
    Book save(BookDTO bookDTO);
    Book update(BookDTO bookDTO);
}

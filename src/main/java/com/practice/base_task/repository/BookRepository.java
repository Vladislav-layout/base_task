package com.practice.base_task.repository;

import com.practice.base_task.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
   // List<Book> findAllByBookshelf(Long id);
   @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookshelf WHERE b.bookId = :id")
   Book findByIdWithLeftJoin(@PathVariable("id") Long id);

}

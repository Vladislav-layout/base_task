package com.practice.base_task.repository;

import com.practice.base_task.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Book, Long> {

}

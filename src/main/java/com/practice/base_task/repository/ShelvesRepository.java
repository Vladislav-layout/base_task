package com.practice.base_task.repository;

import com.practice.base_task.model.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelvesRepository extends JpaRepository<Bookshelf, Long> {
}

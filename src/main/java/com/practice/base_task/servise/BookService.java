package com.practice.base_task.servise;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;


    public Book findByIdWithLeftJoin(Long id) {
        return bookRepository.findByIdWithLeftJoin(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllByBookshelfId(Long id) {
        return bookRepository.findAllByBookshelfId(id);
    }
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Transactional
    public BookDTO createOrUpdateBook(BookDTO dto) {
        Book book = modelMapper.map(dto, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }
}

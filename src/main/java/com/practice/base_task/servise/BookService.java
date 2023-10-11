package com.practice.base_task.servise;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.repository.BookRepository;
import com.practice.base_task.utils.DataMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final DataMapper dataMapper;


    public BookDTO findByIdWithLeftJoin(Long id) {
        Book book = bookRepository.findByIdWithLeftJoin(id);
        return dataMapper.convertEntityToBookDto(book);
    }

    public List<BookDTO> findAll() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        if (books == null || books.isEmpty()) {
            return bookDTOS;
        }

        for (Book book : books) {
            bookDTOS.add(modelMapper.map(book,BookDTO.class));
        }
        return bookDTOS;
    }

    public List<BookDTO> findAllByBookshelfId(Long id) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAllByBookshelfId(id);
        if (books == null || books.isEmpty()) {
            return bookDTOS;
        }

        for (Book book : books) {
            bookDTOS.add(modelMapper.map(book,BookDTO.class));
        }
        return bookDTOS;
    }
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Transactional
    public BookDTO createBook(BookDTO dto) throws SQLException {
        Book savedBook = bookRepository.save(dto);
        return dataMapper.convertEntityToBookDto(savedBook);
    }
    @Transactional
    public BookDTO updateBook(BookDTO dto) {
        Book savedBook = bookRepository.update(dto);
        return dataMapper.convertEntityToBookDto(savedBook);
    }
}

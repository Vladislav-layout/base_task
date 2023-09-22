package com.practice.base_task.servise;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final ShelvesService shelvesService;


    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Book not found - " + id));
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> findAllByBookshelfId(Long id){
        return bookRepository.findAllByBookshelf(id);
    }

//    public Book saveBook(BookDTO dto){
//        return bookRepository.save(Book.builder()
//                        .firstName(dto.getFirstName())
//                        .lastName(dto.getLastName())
//                        .bookName(dto.getBookName())
//                        .bookCount(dto.getBookCount())
//                        .bookshelf(shelvesService.findShelfById(dto.getShelfId()))
//                        .build());
//    }
//
//    public Book updateBook(Book book){
//        return bookRepository.save(book);
//    }

    public void deleteBook(Long id){
            bookRepository.deleteById(id);
    }

    public BookDTO createOrUpdateBook(BookDTO dto) {
        Book book = modelMapper.map(dto, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }


    public Book convertBookDtoToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDTO convertEntityToBookDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }



}

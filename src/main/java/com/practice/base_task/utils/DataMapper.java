package com.practice.base_task.utils;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataMapper {

    private final ModelMapper modelMapper;

    public Book convertBookDtoToEntity(BookDTO bookDTO) {
        Book book =  modelMapper.map(bookDTO, Book.class);
        if (bookDTO.getBookshelf() == null) {
            book.setBookshelf(null);
        }
        return book;
    }

    public BookDTO convertEntityToBookDto(Book book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        if (book.getBookshelf() == null) {
            bookDTO.setBookshelf(null);
        }
        return bookDTO;
    }
}

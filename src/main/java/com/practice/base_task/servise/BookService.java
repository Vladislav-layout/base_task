package com.practice.base_task.servise;

import com.practice.base_task.model.Book;
import com.practice.base_task.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final LibraryRepository libraryRepository;
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public BookService(LibraryRepository libraryRepository, NamedParameterJdbcTemplate template) {
        this.libraryRepository = libraryRepository;
        this.template = template;
    }

    public Optional<Book> findById(Long id) {
        return libraryRepository.findById(id);
    }

    public List<Book> findAll(){
        return libraryRepository.findAll();
    }

    public void saveBook(Book book){
        libraryRepository.save(book);
    }

    public void updateBook(Book book){
        String sql = "UPDATE books set first_name = :firstName, last_name = :lastName, book_name =:bookName, " +
                "\n book_count = :bookCount where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", book.getBookId())
                .addValue("firstName", book.getFirstName())
                .addValue("lastName", book.getLastName())
                .addValue("bookName", book.getBookName())
                .addValue("bookCount", book.getBookCount())
                .addValue("book_shelf", book.getBookshelf());
        template.update(sql, parameterSource);
    }

    public void deleteBook(Long id){
        libraryRepository.deleteById(id);
    }
}

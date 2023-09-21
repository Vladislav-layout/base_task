package com.practice.base_task.servise;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ShelvesService shelvesService;
    private final NamedParameterJdbcTemplate template;


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

    public Book saveBook(BookDTO dto){
        return bookRepository.save(Book.builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .bookName(dto.getBookName())
                        .bookCount(dto.getBookCount())
                        .bookshelf(shelvesService.findShelfById(dto.getShelfId()))
                        .build());
    }

//    public void updateBook(Book book){
//            String sql = "UPDATE books set first_name = :firstName, last_name = :lastName, book_name =:bookName, " +
//                    "\n book_count = :bookCount where book_id = :id";
//            SqlParameterSource parameterSource = new MapSqlParameterSource()
//                    .addValue("id", book.getBookId())
//                    .addValue("firstName", book.getFirstName())
//                    .addValue("lastName", book.getLastName())
//                    .addValue("bookName", book.getBookName())
//                    .addValue("bookCount", book.getBookCount())
//                    .addValue("book_shelf", book.getBookshelf());
//            template.update(sql, parameterSource);
//    }
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
            bookRepository.deleteById(id);
    }
}

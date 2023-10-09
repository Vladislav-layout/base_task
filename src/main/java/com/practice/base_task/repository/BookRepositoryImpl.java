package com.practice.base_task.repository;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.utils.ResourceReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private static final String ALL_BOOKS = ResourceReader.readFileToString("sql/findAllBooks.sql");
    private static final String BOOK_BY_ID = ResourceReader.readFileToString("sql/findBookById.sql");
    private static final String DELETE_BY_ID = ResourceReader.readFileToString("sql/deleteBookById.sql");
    private static final String ALL_BOOKS_BY_BOOKSHELF = ResourceReader.readFileToString("sql/findAllBooksByBookshelf.sql");
    private static final String SAVE_BOOK = ResourceReader.readFileToString("sql/saveBook.sql");
    private static final String UPDATE_BOOK = ResourceReader.readFileToString("sql/updateBook.sql");

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Book> findAllByBookshelfId(Long shelfId) {
        Query query = entityManager.createNativeQuery(ALL_BOOKS_BY_BOOKSHELF, Book.class);
        query.setParameter("shelfId", shelfId);
        long startTime = System.currentTimeMillis();
        List<Book> books = (List<Book>) query.getResultList();
        long endTime = System.currentTimeMillis();
        log.info('\n' +"По запросу: " + ALL_BOOKS_BY_BOOKSHELF  + " книг найдено: " + books.size(), (double) (endTime - startTime) / 1000);
        return books;
    }

    @Override
    public Book findByIdWithLeftJoin(Long id) {
        Query query = entityManager.createNativeQuery(BOOK_BY_ID, Book.class);
        query.setParameter("id", id);
        long startTime = System.currentTimeMillis();
        Book book = (Book) query.getSingleResult();
        long endTime = System.currentTimeMillis();
        log.info("По запросу: " + BOOK_BY_ID + " c значением параметра: " + id + " найдена книга: " + book.getBookName(),  (double) (endTime - startTime) / 1000);
        return book;
    }

    @Override
    public List<Book> findAll() {
        Query query = entityManager.createNativeQuery(ALL_BOOKS, Book.class);
        long startTime = System.currentTimeMillis();
        List<Book> books = (List<Book>) query.getResultList();
        long endTime = System.currentTimeMillis();
        log.info('\n' +"По запросу: " + ALL_BOOKS  + " книг найдено: " + books.size(), (double) (endTime - startTime) / 1000);
        return books;
    }

    @Override
    public void deleteById(Long id) {

        Query query = entityManager.createNativeQuery(DELETE_BY_ID);
        query.setParameter("id", id);
        long startTime = System.currentTimeMillis();
        int deletedRows = query.executeUpdate();
        long endTime = System.currentTimeMillis();

        if (deletedRows > 0) {
            log.info("Книга с ID " + id + " была успешно удалена.", (double) (endTime - startTime) / 1000);
        } else {
            log.warn("Книга с ID " + id + " не найдена или не удалена.", (double) (endTime - startTime) / 1000);
        }
    }

    @Override
    public Book save(BookDTO bookDTO) {
//        try {
            // Используйте ModelMapper для маппинга только установленных полей
//            Map<String, Object> bookParams = modelMapper.map(bookDTO, new TypeToken<Map<String, Object>>() {}.getType());
//
//            StringBuilder sql = new StringBuilder(SAVE_BOOK);
//
//            for (Map.Entry<String, Object> entry : bookParams.entrySet()) {
//                if (entry.getValue() == null) {
//                    // Если значение поля null, то уберите соответствующий параметр из SQL-запроса
//                    String paramName = entry.getKey();
//                    String placeholder = ":" + paramName;
//                    int startIndex = sql.indexOf(placeholder);
//
//                    if (startIndex >= 0) {
//                        sql.delete(startIndex, startIndex + placeholder.length());
//                    }
//                }
//            }
//
//            Query query = entityManager.createNativeQuery(sql.toString());
//
//            // Установите параметры только для установленных полей
//            for (Map.Entry<String, Object> entry : bookParams.entrySet()) {
//                if (entry.getValue() != null) {
//                    query.setParameter(entry.getKey(), entry.getValue());
//                }
//            }
//
//            query.executeUpdate();
//
//            return modelMapper.map(bookDTO, Book.class);
//
//        } catch (Exception e) {
//            // Обработайте исключение, если что-то пойдет не так
//            e.printStackTrace(); // Здесь следует использовать более подходящий обработчик ошибок
//            return null; // Или выбросьте своё исключение в зависимости от ваших потребностей
//        }

        Query query = entityManager.createNativeQuery(SAVE_BOOK);
        query.setParameter("firstName", bookDTO.getBookName());
        query.setParameter("lastName", bookDTO.getLastName());
        query.setParameter("bookName", bookDTO.getBookName());
        query.setParameter("bookCount", bookDTO.getBookCount());
        query.setParameter("shelfId", bookDTO.getBookshelf().getShelfId());
        long startTime = System.currentTimeMillis();
        query.executeUpdate();
        long endTime = System.currentTimeMillis();

        return modelMapper.map(bookDTO, Book.class);
    }



    @Override
    public Book update(BookDTO bookDTO) {
        Query query = entityManager.createNativeQuery(UPDATE_BOOK);
        query.setParameter("bookId", bookDTO.getBookId());
        query.setParameter("firstName", bookDTO.getBookName());
        query.setParameter("lastName", bookDTO.getLastName());
        query.setParameter("bookName", bookDTO.getBookName());
        query.setParameter("bookCount", bookDTO.getBookCount());
        query.setParameter("shelfId", bookDTO.getBookshelf().getShelfId());
        long startTime = System.currentTimeMillis();
        query.executeUpdate();
        long endTime = System.currentTimeMillis();

        return modelMapper.map(bookDTO, Book.class);
    }

}

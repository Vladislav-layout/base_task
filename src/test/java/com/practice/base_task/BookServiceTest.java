package com.practice.base_task;

import com.practice.base_task.dto.BookDTO;
import com.practice.base_task.model.Book;
import com.practice.base_task.model.Bookshelf;
import com.practice.base_task.servise.BookService;
import com.practice.base_task.servise.ShelvesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BaseTaskApplication.class)
class BaseTaskApplicationTests {

    @Autowired
    private BookService bookService;
    @Autowired
    private ShelvesService shelvesService;

    @Test
    public void findAllTest() {
        bookService.findAll();
    }
    @Test
    public void saveBookTest() {

        //Как получить id созданного? чтобы потом удалить

        BookDTO bookDTO = new BookDTO();

        bookDTO.setFirstName("Александр");
        bookDTO.setLastName("Пушкин");
        bookDTO.setBookName("Стихи");
        bookDTO.setBookCount(3);
        bookDTO.setShelfId(1L);

        bookService.saveBook(bookDTO);


    }
	@Test
	public void updateBookTest() {
        //Сначала создать, затем изменить, затем удалить
        // Как получить id созданного? чтобы потом удалить

        Book book = bookService.findById(1L);
        Bookshelf bookshelf = shelvesService.findShelfById(2L);
        book.setBookCount(40);
        book.setFirstName("Test");
        book.setLastName("Test");
        book.setBookName("Test");
        book.setBookshelf(bookshelf);

        bookService.updateBook(book);
	}

    @Test
    public void findByIdTest() {
        bookService.findById(1L);
    }

    @Test
    public void deleteBookTest() {
        //Создать->Удалить->Проверить что такого нету по find(id)
        bookService.deleteBook(1L);

    }







}

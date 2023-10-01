package com.practice.base_task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Сущность книги")
@Data
public class BookDTO {
    @Schema(description = "id книги")
    private Long bookId;
    @Schema(description = "Имя автора")
    private String firstName;
    @Schema(description = "Фамилия автора")
    private String lastName;
    @Schema(description = "Название книги")
    private String bookName;
    @Schema(description = "Кол-во книг")
    private int bookCount;
    @Schema(description = "id шкафа")
    private BookshelfDTO bookshelf;
}

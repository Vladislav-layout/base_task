package com.practice.base_task.dto;

import lombok.Data;


@Data
public class BookDTO {

    private Long bookId;
    private String firstName;
    private String lastName;
    private String bookName;
    private int bookCount;
    private Long shelfId;
}

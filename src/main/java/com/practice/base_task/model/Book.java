package com.practice.base_task.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books", schema = "public")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_count")
    private int bookCount;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Bookshelf bookshelf;


}

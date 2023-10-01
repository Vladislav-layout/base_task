package com.practice.base_task.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookshelves", schema = "public")
@Getter
@Setter
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "bookshelf")
    private List<Book> books = new ArrayList<>();
}

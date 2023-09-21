package com.practice.base_task.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bookshelves")
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    @Column(name = "location")
    private String location;
}

package com.tuancui.identity_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfPages;
    private String language;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

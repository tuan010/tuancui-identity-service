package com.tuancui.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int numberOfPages;
    private String language;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

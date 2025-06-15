package com.tuancui.identity_service.repository;

import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
   List<Book> findByBook(Book book);
}

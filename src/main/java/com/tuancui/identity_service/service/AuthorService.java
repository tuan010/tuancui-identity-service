package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.AuthorCreationRequest;
import com.tuancui.identity_service.dto.response.AuthorCreationResponse;
import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.Book;
import com.tuancui.identity_service.mapper.AuthorMapper;
import com.tuancui.identity_service.repository.AuthorRepository;
import com.tuancui.identity_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public Author save(AuthorCreationRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        return authorRepository.save(author);
    }

    public List<AuthorCreationResponse> findAll() {

        return authorRepository.findAll().stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(Book book) {
        return authorRepository.findByBook(book);
    }

    public void delete(String id) {
        authorRepository.deleteById(id);
    }
}

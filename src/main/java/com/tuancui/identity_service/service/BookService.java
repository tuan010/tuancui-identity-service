package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.BookCreationRequest;
import com.tuancui.identity_service.dto.request.BookDetailRequest;
import com.tuancui.identity_service.dto.response.BookResponse;
import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.Book;
import com.tuancui.identity_service.entity.BookDetail;
import com.tuancui.identity_service.exception.AppException;
import com.tuancui.identity_service.exception.ErrorCode;
import com.tuancui.identity_service.mapper.BookMapper;
import com.tuancui.identity_service.repository.AuthorRepository;
import com.tuancui.identity_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public Book save(BookCreationRequest request) {

        Book book = new Book();
        if (!CollectionUtils.isEmpty(request.getAuthors())) {
            book.setAuthors(request.getAuthors());
        }

        return bookRepo.save(book);
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public List<Book> findByAuthor(String author) {
        return bookRepo.findByAuthor(author);
    }

    public void delete(String id) {
        bookRepo.deleteById(id);
    }

    public BookResponse addAuthorToBook(String bookId, String authorId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));

        book.getAuthors().add(author);
        author.getBook().add(book);

        authorRepository.save(author);
       Book savedBook = bookRepo.save(book);
       return bookMapper.toResponse(savedBook);
    }

    public void addBookDetail(String bookId, BookDetailRequest detail){
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        BookDetail bookDetail = new BookDetail();
        bookDetail.setLanguage(detail.getLanguage());
        bookDetail.setNumberOfPages(detail.getNumberOfPages());

        book.setBookDetail(bookDetail);

        bookRepo.save(book);
    }
}

package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.request.BookCreationRequest;
import com.tuancui.identity_service.dto.request.BookDetailRequest;
import com.tuancui.identity_service.dto.response.BookResponse;
import com.tuancui.identity_service.entity.*;
import com.tuancui.identity_service.exception.AppException;
import com.tuancui.identity_service.exception.ErrorCode;
import com.tuancui.identity_service.mapper.BookMapper;
import com.tuancui.identity_service.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    private final AuthorRepository authorRepository;

    private final BookMapper bookMapper;

    private final BookDetailRepository bookDetailRepository;

    private final CategoryRepository categoryRepository;

    private final PublisherRepository publisherRepository;

    public Book save(BookCreationRequest request) {
        boolean allAuthorAreExist = request.getAuthorIds().stream().allMatch(authorRepository::existsById);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));

        Publisher publisher = publisherRepository.findById(request.getPublisherId()).orElseThrow(
                () -> new AppException(ErrorCode.PUBLISHER_NOT_EXIST));

        if (!allAuthorAreExist) throw new AppException(ErrorCode.AUTHOR_NOT_FOUND);

        List<Author> listAuthor = authorRepository.findAllById(request.getAuthorIds());

        Book book = Book.builder()
                .title(request.getTitle())
                .authors(new HashSet<>(listAuthor))
                .category(category)
                .publisher(publisher)
                .build();

        Book savedBook = bookRepo.save(book);

        //Save book detail first
        BookDetail bookDetail = BookDetail.builder()
                .language(request.getLanguage())
                .numberOfPages(request.getNumberOfPages())
                .book(book)
                .build();
        //save book detail first
        bookDetailRepository.save(bookDetail);


        return savedBook;
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

    public BookResponse addBookDetail(String bookId, BookDetailRequest detail) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        BookDetail bookDetail = new BookDetail();
        bookDetail.setLanguage(detail.getLanguage());
        bookDetail.setNumberOfPages(detail.getNumberOfPages());
        bookDetail.setBook(book);
        //save book detail first
        BookDetail savedBookDetail = bookDetailRepository.save(bookDetail);

        //set book detail
        book.setBookDetail(savedBookDetail);

        Book savedBook = bookRepo.save(book);
        BookResponse response = new BookResponse();
        response.setId(savedBook.getId());
        response.setTitle(savedBook.getTitle());
        response.setAuthor(savedBook.getAuthor());
        response.setLanguage(savedBook.getBookDetail().getLanguage());
        response.setNumberOfPages(savedBook.getBookDetail().getNumberOfPages());
        return response;
    }

}

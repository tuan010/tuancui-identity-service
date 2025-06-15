package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.request.BookCreationRequest;
import com.tuancui.identity_service.dto.request.BookDetailRequest;
import com.tuancui.identity_service.dto.response.ApiResponse;
import com.tuancui.identity_service.dto.response.BookResponse;
import com.tuancui.identity_service.entity.Book;
import com.tuancui.identity_service.entity.BookDetail;
import com.tuancui.identity_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book create(@RequestBody BookCreationRequest book) {
        return bookService.save(book);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/author/{name}")
    public List<Book> getByAuthor(@PathVariable String name) {
        return bookService.findByAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookService.delete(id);
    }

    @PostMapping("{bookId}/add/{authorId}")
    public ApiResponse<BookResponse> addAuthorToBook(@PathVariable("bookId") String bookId, @PathVariable("authorId") String authorId){
            BookResponse bookResponse = bookService.addAuthorToBook(bookId, authorId);
            return  ApiResponse.<BookResponse>builder()
                    .message("bookResponse")
                    .result(bookResponse)
                    .build();
    }

    @PostMapping("{bookId}/add")
    public ApiResponse<BookResponse> addBookDetail(@PathVariable("bookId") String bookId, @RequestBody BookDetailRequest bookDetail){
        bookService.addBookDetail(bookId, bookDetail);
        return  ApiResponse.<BookResponse>builder()
                .message("bookResponse")
                .result(null)
                .build();
    }

}
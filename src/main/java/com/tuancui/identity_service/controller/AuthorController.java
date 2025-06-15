package com.tuancui.identity_service.controller;

import com.tuancui.identity_service.dto.request.AuthorCreationRequest;
import com.tuancui.identity_service.dto.response.AuthorCreationResponse;
import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public Author create(@RequestBody AuthorCreationRequest request){
        return authorService.save(request);
    }

    @GetMapping
    public List<AuthorCreationResponse> getAllAuthors(){
     return authorService.findAll();
    }
}

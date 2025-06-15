package com.tuancui.identity_service.config;

import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.Category;
import com.tuancui.identity_service.entity.Publisher;
import com.tuancui.identity_service.repository.AuthorRepository;
import com.tuancui.identity_service.repository.CategoryRepository;
import com.tuancui.identity_service.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j(topic = "DATA-INITIALIZER")
public class DataInitializer {

    @Bean
    ApplicationRunner initData(AuthorRepository authorRepository,
                               CategoryRepository categoryRepository,
                               PublisherRepository publisherRepository
    ){
        return args -> {
//            log.info("Start initialize data");
//            Author a1 = new Author();
//            a1.setName("J.K. Rowling");
//            Author a2 = new Author();
//            a2.setName("George Orwell");
//            authorRepository.saveAll(List.of(a1, a2));
//
//            Category c1 = new Category();
//            c1.setName("Fantasy");
//            Category c2 = new Category();
//            c2.setName("Dystopian");
//            categoryRepository.saveAll(List.of(c1, c2));
//
//            Publisher p1 = new Publisher();
//            p1.setName("Bloomsbury");
//            Publisher p2 = new Publisher();
//            p2.setName("Secker & Warburg");
//            publisherRepository.saveAll(List.of(p1, p2));
//            log.info("Initialization done");
        };
    }
}

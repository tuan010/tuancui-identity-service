package com.tuancui.identity_service.dto.response;

import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.BookDetail;
import com.tuancui.identity_service.entity.Category;
import com.tuancui.identity_service.entity.Publisher;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    private String id;
    private String title;
    private String author;
    private List<String> authorIds;
    private Integer numberOfPages;
    private String language;
    private String category;
    private String publisher;

}

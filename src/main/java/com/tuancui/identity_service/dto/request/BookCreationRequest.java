package com.tuancui.identity_service.dto.request;

import com.tuancui.identity_service.entity.Author;
import com.tuancui.identity_service.entity.BookDetail;
import com.tuancui.identity_service.entity.Category;
import com.tuancui.identity_service.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreationRequest {
    private String title;
    private String author;
    private List<String> authorIds;
    private Integer publisher;
    private Set<Author> authors;
    private BookDetail bookDetail;
}

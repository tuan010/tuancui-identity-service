package com.tuancui.identity_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreationRequest {
    private String title;
    private String author;
    private List<String> authorIds;
    private Integer numberOfPages;
    private String language;
    private String categoryId;
    private String publisherId;
}

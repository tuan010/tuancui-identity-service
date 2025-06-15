package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.response.BookResponse;
import com.tuancui.identity_service.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toResponse(Book book);
}

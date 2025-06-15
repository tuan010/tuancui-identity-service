package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.response.AuthorCreationResponse;
import com.tuancui.identity_service.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorCreationResponse toResponse(Author entity);
}

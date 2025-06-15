package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.response.CategoryResponse;
import com.tuancui.identity_service.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
}
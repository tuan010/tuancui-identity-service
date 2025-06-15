package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.response.CategoryResponse;
import com.tuancui.identity_service.dto.response.PublisherResponse;
import com.tuancui.identity_service.entity.Category;
import com.tuancui.identity_service.entity.Publisher;
import com.tuancui.identity_service.mapper.CategoryMapper;
import com.tuancui.identity_service.mapper.PublisherMapper;
import com.tuancui.identity_service.repository.CategoryRepository;
import com.tuancui.identity_service.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    public CategoryResponse create(String name){
        Category publisher = Category.builder()
                .name(name)
                .build();

       return categoryMapper.toResponse(categoryRepository.save(publisher));

    }
}

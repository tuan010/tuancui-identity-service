package com.tuancui.identity_service.mapper;

import com.tuancui.identity_service.dto.response.PublisherResponse;
import com.tuancui.identity_service.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherResponse toResponse(Publisher publisher);
}

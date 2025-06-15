package com.tuancui.identity_service.service;

import com.tuancui.identity_service.dto.response.PublisherResponse;
import com.tuancui.identity_service.entity.Publisher;
import com.tuancui.identity_service.mapper.PublisherMapper;
import com.tuancui.identity_service.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;
    public PublisherResponse create(String name){
        Publisher publisher = Publisher.builder()
                .name(name)
                .build();

       return publisherMapper.toResponse(publisherRepository.save(publisher));

    }
}

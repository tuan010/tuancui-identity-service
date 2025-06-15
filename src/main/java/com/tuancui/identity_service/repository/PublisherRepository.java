package com.tuancui.identity_service.repository;

import com.tuancui.identity_service.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
}

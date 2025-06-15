package com.tuancui.identity_service.repository;

import com.tuancui.identity_service.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, String> {

}

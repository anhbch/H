package com.application.puranh.repository;

import com.application.puranh.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByAuthorId(Long id);

    List<Todo> findByAuthorIdAndCompletedIsFalse(Long id);

    List<Todo> findByAuthorIdAndCompletedIsTrue(Long id);

}

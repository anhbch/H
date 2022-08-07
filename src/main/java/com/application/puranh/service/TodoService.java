package com.application.puranh.service;

import com.application.puranh.model.Todo;
import com.application.puranh.service.DTO.TodoDto;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();

    Optional<TodoDto> getTodoById(Long id);

    List<TodoDto> getAllDto();

    List<TodoDto> getTodosByAuthorId(Long id);

    TodoDto save(TodoDto todo);

    List<TodoDto> getPendingByAuthorId(Long id);

    List<TodoDto> getCompletedByAuthorId(Long id);

    void deleteTask(Long id);
}

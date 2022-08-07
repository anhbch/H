package com.application.puranh.service.implement;

import com.application.puranh.service.TodoService;
import com.application.puranh.model.Todo;
import com.application.puranh.repository.TodoRepository;
import com.application.puranh.service.DTO.TodoDto;
import com.application.puranh.service.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<Todo> getAll() {
        Sort sortByCreatedAt =  Sort.by(Sort.Direction.DESC, "createDate");
        return todoRepository.findAll(sortByCreatedAt);
    }

    @Override
    public Optional<TodoDto> getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(todoMapper::toDto);
    }

    @Override
    public List<TodoDto> getAllDto() {
        Sort sortByCreatedAt =  Sort.by(Sort.Direction.DESC, "createDate");
        return todoRepository.findAll(sortByCreatedAt).stream()
                .map(todoMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<TodoDto> getTodosByAuthorId(Long id) {
        List<Todo> todos = todoRepository.findAllByAuthorId(id);
        return todoMapper.toListDto(todos);
    }

    @Override
    public TodoDto save(TodoDto todo) {
        Todo todoList = todoMapper.toEntity(todo);
        todoList = todoRepository.save(todoList);
        return todoMapper.toDto(todoList);
    }

    @Override
    public List<TodoDto> getPendingByAuthorId(Long id) {
        List<Todo> pendings = todoRepository.findByAuthorIdAndCompletedIsFalse(id);
        return todoMapper.toListDto(pendings);
    }

    @Override
    public List<TodoDto> getCompletedByAuthorId(Long id) {
        List<Todo> completedTasks = todoRepository.findByAuthorIdAndCompletedIsTrue(id);
        return todoMapper.toListDto(completedTasks);
    }

    @Override
    public void deleteTask(Long id) {
        todoRepository.deleteById(id);
    }
}

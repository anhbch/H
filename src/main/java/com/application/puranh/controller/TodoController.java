package com.application.puranh.controller;

import com.application.puranh.service.DTO.TodoDto;
import com.application.puranh.service.TodoService;
import com.application.puranh.model.Todo;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/todo")
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<TodoDto> getById(@PathVariable Long id) {
        logger.debug("Request to get todo by id");
        Optional<TodoDto> todo = todoService.getTodoById(id);
        return ResponseUtil.wrapOrNotFound(todo);
    }

    @GetMapping(path = "/by-author/{authorId}")
    public ResponseEntity<List<TodoDto>> getByAuthorId(@PathVariable("authorId") Long id) {
        logger.debug("Request to get todos by author id");
        return ResponseEntity.ok(todoService.getTodosByAuthorId(id));
    }


    @GetMapping(path ="/pending-todo/{authorId}")
    public ResponseEntity<List<TodoDto>> getPendingTodo (@PathVariable("authorId") Long id) {
        logger.debug("Request to get pending task by author's id");
        return ResponseEntity.ok(todoService.getPendingByAuthorId(id));
    }

    @GetMapping(path ="/completed-todo/{authorId}")
    public ResponseEntity<List<TodoDto>> getCompletedTodo (@PathVariable("authorId") Long id) {
        logger.debug("Request to get completed task by author's id");
        return ResponseEntity.ok(todoService.getCompletedByAuthorId(id));
    }

    @PutMapping(path = "/edit-todo")
    public ResponseEntity<TodoDto> updateTodo (@RequestBody TodoDto todo) {
        logger.debug("Request to update a todo");
        if (todo.getTodoId() == null) {
            throw new IllegalArgumentException("Todo id cannot be null");
        }
        if (todoService.getTodoById(todo.getTodoId()).isPresent()) {
            TodoDto result = todoService.save(todo);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/create-todo")
    public ResponseEntity<TodoDto> createNewTodo(@RequestBody TodoDto todo) throws URISyntaxException {
        logger.debug("Request to create new todo");
        if (todo.getTodoId() != null) {
            throw new IllegalArgumentException("Todo cannot already have an id");
        }
        todo.setCompleted(false);
        todo.setLongBreakOn(false);
        todo.setBreakTime(600); // 5 minutes
        todo.setFocusTime(1500); // 25 minutes
        todo.setLongBreak(1200); // 20 minutes
        todo.setLongBreakInterval(4);
        LocalDate now = LocalDate.now();
        todo.setCreateDate(Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        TodoDto result = todoService.save(todo);
        return ResponseEntity.created(new URI("todos/create" + result.getTodoId()))
                .body(result);
    }

    @DeleteMapping(path ="/delete/{id}")
    public ResponseEntity<Void> deleteTodo (@PathVariable(name = "id") Long id) {
        logger.debug("Request to delete a task");
        try {
            todoService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}

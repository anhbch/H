package com.application.puranh.controller;

import com.application.puranh.model.Event;
import com.application.puranh.model.Todo;
import com.application.puranh.model.User;
import com.application.puranh.service.DTO.EventDto;
import com.application.puranh.service.DTO.TodoDto;
import com.application.puranh.service.EventService;
import com.application.puranh.service.TodoService;
import com.application.puranh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="api/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoListService;
    @Autowired
    private EventService eventService;

    /**
     * Get All Users
     * @return
     */
    @GetMapping(path = "/users/get-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> temp =  userService.getUsers();
        return ResponseEntity.ok(temp);
    }

    /**
     * Get All Todos
     * @return
     */
    @GetMapping(path = "/todos/get-all")
    public List<Todo> getAllTodos() {
        logger.debug("Request to get all todos");
        return todoListService.getAll();
    }

    @GetMapping(path = "/todos/get-all-dto")
    public List<TodoDto> getAllDto() {
        logger.debug("Request to get all todos dto");
        return todoListService.getAllDto();
    }

    /**
     * Get All Events
     * @return
     */
    @GetMapping(path = "/events/get-all")
    public List<Event> getAllEvents() {
        logger.debug("Request to get all events");
        return eventService.getEvents();
    }

    @GetMapping(path = "/events/get-all-dto")
    public List<EventDto> getAllEventDto() {
        logger.debug("Request to get all events dto");
        return eventService.findAll();
    }
}

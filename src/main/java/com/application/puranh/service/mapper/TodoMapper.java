package com.application.puranh.service.mapper;

import com.application.puranh.service.DTO.TodoDto;
import com.application.puranh.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
@Component
public interface TodoMapper extends EntityMapper<TodoDto, Todo> {

    @Mapping(target = "authorId", source = "author.id")
    TodoDto toDto (Todo todo);

    @Mapping (target = "author", source = "authorId")
    Todo toEntity (TodoDto dto);

    default Todo fromId (Long id) {
        if (id == null) {
            return null;
        }
        Todo todo = new Todo();
        todo.setTodoId(id);
        return todo;
    }
}

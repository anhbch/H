package com.happlication.h.service.mapper;


import com.happlication.h.model.Event;
import com.happlication.h.service.DTO.EventDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper (componentModel = "spring", uses = {UserMapper.class})
@Component
public interface EventMapper extends EntityMapper<EventDto, Event>{

    @Mapping(target = "userId", source = "user.id")
    EventDto toDto (Event event);

    @Mapping (target = "user", source = "userId")
    Event toEntity (EventDto dto);

    default Event fromId (Long id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setEventId(id);
        return event;
    }
}
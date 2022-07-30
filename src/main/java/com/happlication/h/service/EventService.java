package com.happlication.h.service;


import com.happlication.h.model.Event;
import com.happlication.h.service.DTO.EventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getEvents();

    Optional<EventDto> findOne(Long eventId);

    List<EventDto> findByUser(Long userId);

    List<EventDto> findAll();

    EventDto save(EventDto eventDto);

    void deleteAnEvent(Long eventId);

    EventDto update(EventDto eventDto);
}

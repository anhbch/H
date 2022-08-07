package com.application.puranh.service;


import com.application.puranh.service.DTO.EventDto;
import com.application.puranh.model.Event;

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

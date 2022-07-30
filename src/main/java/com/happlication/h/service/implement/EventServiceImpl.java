package com.happlication.h.service.implement;

import com.happlication.h.model.Event;
import com.happlication.h.repository.EventRepository;
import com.happlication.h.service.DTO.EventDto;
import com.happlication.h.service.EventService;
import com.happlication.h.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<EventDto> findOne(Long eventId) {
        return eventRepository.findById(eventId)
                .map(eventMapper::toDto);
    }

    @Override
    public List<EventDto> findByUser(Long userId) {
        List<Event> events = eventRepository.findByUserId(userId);
        return eventMapper.toListDto(events);
    }

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public EventDto save(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public void deleteAnEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }
}

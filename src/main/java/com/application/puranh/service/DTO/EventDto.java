package com.application.puranh.service.DTO;

import javax.persistence.Column;
import java.time.LocalDate;


public class EventDto {

    private Long eventId;

    @Column(name = "user_name")
    private String eventName;

    @Column(name = "description")
    private String description;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "user_id")
    private Long userId;

    public EventDto() {
    }

    public EventDto(Long eventId, String eventName, String description, LocalDate eventDate, Long userId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.userId = userId;
    }

    public EventDto(String eventName, String description, LocalDate eventDate, Long userId) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

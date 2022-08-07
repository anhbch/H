package com.happlication.h.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
public class MediaUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mediaId;
    private String title;
    private String url;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
//    @JsonIgnoreProperties(value = "media", allowSetters = true)
    @JsonIgnore
    @JoinColumn
    private Event event;

    public MediaUpload() {
    }

    public MediaUpload(Long mediaId, String title, String url, Event event) {
        this.mediaId = mediaId;
        this.title = title;
        this.url = url;
        this.event = event;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

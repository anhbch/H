package com.application.puranh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoId;

    @NotBlank
    @Size(max = 100)
    private String title;
    private boolean completed;
    private Date createDate;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
//    @JsonIgnore
    @JoinColumn(updatable = false)
    private User author;

    private int focusTime;
    private int breakTime;
    private int longBreak;
    private boolean longBreakOn;
    private int longBreakInterval;

    public Todo() {
    }

    public Todo(Long todoId, String title, boolean completed, Date createDate, User author,
                int focusTime, int breakTime, int longBreak, boolean longBreakOn, int longBreakInterval) {
        this.todoId = todoId;
        this.title = title;
        this.completed = completed;
        this.createDate = createDate;
        this.author = author;
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.longBreak = longBreak;
        this.longBreakOn = longBreakOn;
        this.longBreakInterval = longBreakInterval;
    }

    public Todo(String title, boolean completed, Date createDate, User author, int focusTime,
                int breakTime, int longBreak, boolean longBreakOn, int longBreakInterval) {
        this.title = title;
        this.completed = completed;
        this.createDate = createDate;
        this.author = author;
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.longBreak = longBreak;
        this.longBreakOn = longBreakOn;
        this.longBreakInterval = longBreakInterval;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(int focusTime) {
        this.focusTime = focusTime;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public int getLongBreak() {
        return longBreak;
    }

    public void setLongBreak(int longBreak) {
        this.longBreak = longBreak;
    }

    public boolean isLongBreakOn() {
        return longBreakOn;
    }

    public void setLongBreakOn(boolean longBreakOn) {
        this.longBreakOn = longBreakOn;
    }

    public int getLongBreakInterval() {
        return longBreakInterval;
    }

    public void setLongBreakInterval(int longBreakInterval) {
        this.longBreakInterval = longBreakInterval;
    }
}

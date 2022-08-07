package com.application.puranh.service.DTO;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

public class TodoDto {

    private Long todoId;
    private String title;
    private boolean completed;
    private Date createDate;
    private Long authorId;

    private int focusTime;
    private int breakTime;
    private int longBreak;
    private boolean longBreakOn;
    private int longBreakInterval;

    public TodoDto() {
    }

    public TodoDto(Long todoId, String title, boolean completed, Date createDate, Long authorId,
                   int focusTime, int breakTime, int longBreak, boolean longBreakOn, int longBreakInterval) {
        this.todoId = todoId;
        this.title = title;
        this.completed = completed;
        this.createDate = createDate;
        this.authorId = authorId;
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.longBreak = longBreak;
        this.longBreakOn = longBreakOn;
        this.longBreakInterval = longBreakInterval;
    }

    public TodoDto(String title, boolean completed, Date createDate, Long authorId,
                   int focusTime, int breakTime, int longBreak, boolean longBreakOn, int longBreakInterval) {
        this.title = title;
        this.completed = completed;
        this.createDate = createDate;
        this.authorId = authorId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

package com.application.puranh.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Validated
@Table(name = "user_control")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotEmpty
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @NotEmpty
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @NotEmpty
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "ava_url")
    private String avaUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Event> events;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Todo> todos;


    @Column(name = "date")
    private LocalDate date;

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public User() {

    }

    public User(String userName, String lastName, String firstName, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public User(Long id, String userName, String lastName, String firstName,
                String password, String email, String avaUrl, List<Event> events, List<Todo> todos, LocalDate date) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.avaUrl = avaUrl;
        this.events = events;
        this.todos = todos;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}

package ru.krastti.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 30)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank(message = "Email пользователя не может быть пустым")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 30)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Project> projects;

    public User() { }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> project) {
        this.projects = project;
    }

}

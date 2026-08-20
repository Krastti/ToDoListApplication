package ru.krastti.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import ru.krastti.enums.Priority;
import ru.krastti.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Название задачи не может быть пустым!")
    @Size(max = 30)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Статус задачи не может быть пустым!")
    private Status status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    // @NotNull(message = "Приоритет задачи не может быть пустым")
    private Priority priority;

    @Column(name = "deadline")
    @FutureOrPresent(message = "Дата не может быть в прошлом!")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() { }

    public Task(String title, String description, Status status,
                LocalDate deadline, Project project) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.project = project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

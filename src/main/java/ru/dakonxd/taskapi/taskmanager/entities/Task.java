package ru.dakonxd.taskapi.taskmanager.entities;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.dakonxd.taskapi.security.entities.User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "created")
    LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User owner;

}

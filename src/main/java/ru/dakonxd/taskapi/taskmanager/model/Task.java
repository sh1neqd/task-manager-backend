package ru.dakonxd.taskapi.taskmanager.model;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.dakonxd.taskapi.security.entities.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
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
    User user;

}

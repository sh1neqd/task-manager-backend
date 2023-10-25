package ru.dakonxd.taskapi.security.entities;

import lombok.Data;
import ru.dakonxd.taskapi.taskmanager.entities.Task;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Username must not be empty")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be in email format")
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

}

package ru.dakonxd.taskapi.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskapi.security.entities.User;
import ru.dakonxd.taskapi.security.service.UserService;
import ru.dakonxd.taskapi.taskmanager.entities.Task;
import ru.dakonxd.taskapi.taskmanager.entities.dtos.TaskDto;
import ru.dakonxd.taskapi.taskmanager.repositories.TaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Transactional
    public ResponseEntity<?> save(TaskDto taskDto, String username) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setActive(true);
        task.setModified(LocalDateTime.now());
        Optional<User> user = userService.findByUsername(username);
        task.setOwner(user.get());
        taskRepository.save(task);
        return ResponseEntity.ok("task " + task.getName() + " created");
    }

    private Optional<Task> findTaskByName(String name) {
        return taskRepository.findByName(name);
    }
}

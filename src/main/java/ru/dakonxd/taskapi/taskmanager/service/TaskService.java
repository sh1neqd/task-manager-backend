package ru.dakonxd.taskapi.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskapi.exceptions.AppError;
import ru.dakonxd.taskapi.security.entities.User;
import ru.dakonxd.taskapi.security.service.UserService;
import ru.dakonxd.taskapi.taskmanager.entities.Task;
import ru.dakonxd.taskapi.taskmanager.entities.dtos.TaskDto;
import ru.dakonxd.taskapi.taskmanager.repositories.TaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public ResponseEntity<?> deleteTask(String taskname, String username) {
        Task task = findTaskWithThisName(taskname, username);
        if(task==null) return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Task with this name not found"), HttpStatus.BAD_REQUEST);
        taskRepository.deleteById(task.getId());
        return ResponseEntity.ok("Task" + taskname + "deleted");
    }

    public ResponseEntity<?> findTasksByUsername(String username) {
        var tasks = userService.findUserByUsername(username).get().getTasks().stream().map(Task::getName).collect(Collectors.toList());
        if(tasks.isEmpty()) return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "You have no tasks"), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<?> makeTaskDone(String taskname, String username) {
        Task task = findTaskWithThisName(taskname,username);
        if(task==null) return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Task with this name not found"), HttpStatus.BAD_REQUEST);
        else {
            task.setActive(false);
            task.setModified(LocalDateTime.now());
            taskRepository.save(task);
        }
        return ResponseEntity.ok("Task " + taskname + " is done");
    }

    private Task findTaskWithThisName(String taskname, String username) {
        User user = userService.findUserByUsername(username).get();
        var tasks = user.getTasks();
        for(Task task : tasks) {
            if(task.getName().equals(taskname)) return task;
        }
        return null;
    }
}

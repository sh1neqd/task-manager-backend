package ru.dakonxd.taskapi.taskmanager.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.dakonxd.taskapi.taskmanager.entities.dtos.TaskDto;
import ru.dakonxd.taskapi.taskmanager.service.TaskService;

@OpenAPIDefinition(info = @Info(
        title = "Task manager",
        description = "<H4>microservice project</H4>"),
        servers = {@Server(url = "http://localhost:8189", description = "Local server")})
@Tag(name = "Create task!")
@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Task creating")
    @PostMapping("/createtask")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskService.save(taskDto, authentication.getName());
    }

    @Operation(summary = "Getting all tasks(only authorized")
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskService.findTasksByUsername(authentication.getName());
    }

    @Operation(summary = "Making task done(only authorized)")
    @PostMapping("/tasks/{taskname}/done")
    public ResponseEntity<?> makeTaskDone(@PathVariable("taskname") String taskname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskService.makeTaskDone(taskname, authentication.getName());
    }

    @Operation(summary = "Task creating(only authorized)")
    @PostMapping("/tasks/{taskname}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable("taskname") String taskname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return taskService.deleteTask(taskname, authentication.getName());
    }
}

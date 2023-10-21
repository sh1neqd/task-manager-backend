package ru.dakonxd.taskapi.taskmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dakonxd.taskapi.security.entities.User;
import ru.dakonxd.taskapi.taskmanager.entities.dtos.TaskDto;
import ru.dakonxd.taskapi.taskmanager.service.TaskService;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createtask")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
        System.out.println(authentication.getName());
        return taskService.save(taskDto, authentication.getName());
    }
}

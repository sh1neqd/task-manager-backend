package ru.dakonxd.taskapi.security.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dakonxd.taskapi.security.entities.dtos.JwtRequest;
import ru.dakonxd.taskapi.security.entities.dtos.RegistrationUserDto;
import ru.dakonxd.taskapi.security.service.AuthService;

@OpenAPIDefinition(info = @Info(
        title = "Task manager",
        description = "<H4>microservice project</H4>"),
        servers = {@Server(url = "http://localhost:8189", description = "Local server")})
@Tag(name = "Create task!")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Authorization endpoint")
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }
    @Operation(summary = "Registration endpoint")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
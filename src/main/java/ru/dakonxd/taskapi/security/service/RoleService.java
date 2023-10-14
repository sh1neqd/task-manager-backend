package ru.dakonxd.taskapi.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskapi.security.repositories.RoleRepository;
import ru.dakonxd.taskapi.security.entities.Role;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}

package ru.dakonxd.taskapi.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dakonxd.taskapi.taskmanager.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}

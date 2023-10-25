package ru.dakonxd.taskapi.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dakonxd.taskapi.taskmanager.entities.Task;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}

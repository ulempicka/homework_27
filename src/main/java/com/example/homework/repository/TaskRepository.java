package com.example.homework.repository;

import com.example.homework.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDoneEquals(boolean isDone);
}

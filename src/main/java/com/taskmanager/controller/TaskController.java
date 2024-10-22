package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

   
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

   
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

   
    @PostMapping("/{id}/dependencies/{dependencyId}")
    public ResponseEntity<Task> addDependency(@PathVariable Long id, @PathVariable Long dependencyId) {
        Task updatedTask = taskService.addDependency(id, dependencyId);
        return ResponseEntity.ok(updatedTask);
    }

   
    @DeleteMapping("/{id}/dependencies/{dependencyId}")
    public ResponseEntity<Task> removeDependency(@PathVariable Long id, @PathVariable Long dependencyId) {
        Task updatedTask = taskService.removeDependency(id, dependencyId);
        return ResponseEntity.ok(updatedTask);
    }

    
    @GetMapping("/{id}/dependencies")
    public ResponseEntity<Set<Task>> getDependencies(@PathVariable Long id) {
        Set<Task> dependencies = taskService.getDependencies(id);
        return ResponseEntity.ok(dependencies);
    }
}

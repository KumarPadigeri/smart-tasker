package com.smarttasker.adapter.in;


/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */


import com.smarttasker.dto.task.TaskRequest;
import com.smarttasker.dto.task.TaskResponse;
import com.smarttasker.port.in.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskResponse createdTask = taskService.createTask(taskRequest, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TaskResponse> tasks = taskService.getAllTasks(email);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @DeleteMapping("task/{taskID}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskID) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        taskService.deleteTask(taskID, email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("task/{taskID}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long taskID) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskResponse task = taskService.getTask(taskID);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PutMapping("task/{taskID}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskID, @RequestBody TaskRequest taskRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskResponse updatedTask = taskService.updateTask(taskID, taskRequest, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
    }


    @GetMapping("tasks/completed")
    public ResponseEntity<List<TaskResponse>> completedTasks() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TaskResponse> tasks = taskService.getAllCompletedTasks(email);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PatchMapping("task/{taskID}/complete")
    public ResponseEntity<Void> markAsCompleted(@PathVariable Long taskID) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        taskService.markAsCompleted(taskID, email);
        return ResponseEntity.ok().build();
    }


}

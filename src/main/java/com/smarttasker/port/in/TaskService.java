package com.smarttasker.port.in;


/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */

import com.smarttasker.dto.task.TaskRequest;
import com.smarttasker.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest, String email);

    List<TaskResponse> getAllTasks(String email);

    void deleteTask(Long id, String email);

    TaskResponse getTask(Long id);

    TaskResponse updateTask(Long id, TaskRequest taskRequest, String userEmail);

    List<TaskResponse> getAllCompletedTasks(String email);

    void markAsCompleted(Long taskID, String email);
}

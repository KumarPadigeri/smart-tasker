package com.smarttasker.service;


import com.smarttasker.adapter.out.persistence.TaskRepository;
import com.smarttasker.adapter.out.persistence.UserRepository;
import com.smarttasker.domain.model.Task;
import com.smarttasker.domain.model.Users;
import com.smarttasker.dto.task.TaskRequest;
import com.smarttasker.dto.task.TaskResponse;
import com.smarttasker.mapper.TaskMapper;
import com.smarttasker.port.in.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest, String userEmail) {
        Users user = getUser(userEmail);
        return taskMapper.TaskEntityToDTO(taskRepository.save(taskMapper.TaskDTOToEntity(taskRequest, user)));
    }


    @Override
    public List<TaskResponse> getAllTasks(String userEmail) {
        Users user = getUser(userEmail);
        return taskMapper.toTaskResponse(taskRepository.findByUser(user));
    }

    @Override
    public void deleteTask(Long taskId, String userEmail) {
        Users user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this task");
        }

        taskRepository.delete(task);
    }

    @Override
    public TaskResponse getTask(Long taskID) {
        if (taskRepository.findById(taskID).isPresent()) {
            return taskMapper.TaskEntityToDTO(taskRepository.findById(taskID).get());
        } else {
            return null;
        }
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest request, String userEmail) {
        Users user = getUser(userEmail);

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existingTask.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this task");
        }

        taskMapper.taskReqToTaskEntity(request, existingTask);
        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.TaskEntityToDTO(updatedTask);
    }

    @Override
    public List<TaskResponse> getAllCompletedTasks(String email) {
        List<Task> tasks = taskRepository.findByUserAndCompletedTrue(getUser(email));
        return taskMapper.toTaskResponse(tasks);
    }

    @Override
    public void markAsCompleted(Long taskID, String userEmail) {
        Users user = getUser(userEmail);

        Task existingTask = taskRepository.findById(taskID)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existingTask.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this task");
        }
        existingTask.setCompleted(true);
        taskRepository.save(existingTask);

    }


    private Users getUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}

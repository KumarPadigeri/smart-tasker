package com.smarttasker.mapper;


/*
 * @Created 7/6/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */

import com.smarttasker.domain.model.Task;
import com.smarttasker.domain.model.Users;
import com.smarttasker.dto.task.TaskRequest;
import com.smarttasker.dto.task.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskResponse TaskEntityToDTO(Task task);

    @Mapping(target = "completed", constant = "false")
    @Mapping(target = "user", expression = "java(user)")
    @Mapping(target = "id", ignore = true)
    Task TaskDTOToEntity(TaskRequest request, Users user);


    List<TaskResponse> toTaskResponse(List<Task> task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void taskReqToTaskEntity(TaskRequest request, @MappingTarget Task task);

}

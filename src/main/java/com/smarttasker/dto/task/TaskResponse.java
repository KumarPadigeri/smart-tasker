package com.smarttasker.dto.task;


import com.smarttasker.domain.model.TaskPriority;

import java.time.LocalDateTime;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */public record TaskResponse(Long id,
                              String title,
                              String description,
                              LocalDateTime dueDate,
                              TaskPriority priority,
                              boolean completed
) {
}
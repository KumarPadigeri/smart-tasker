package com.smarttasker.adapter.out.persistence;


/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */

import com.smarttasker.domain.model.Task;
import com.smarttasker.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(Users user);

    List<Task> findByUserAndCompletedTrue(Users user);
}

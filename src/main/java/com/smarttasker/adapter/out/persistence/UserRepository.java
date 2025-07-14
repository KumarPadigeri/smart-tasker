package com.smarttasker.adapter.out.persistence;

import com.smarttasker.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @Created 2/11/24
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}

package com.smarttasker.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Created 2/11/24
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    @Column(nullable = false)
    private String password;
    private String role = "USER";
    private boolean active = true;

}

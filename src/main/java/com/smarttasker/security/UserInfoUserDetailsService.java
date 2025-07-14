package com.smarttasker.security;

import com.smarttasker.adapter.out.persistence.UserRepository;
import com.smarttasker.domain.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
 * @Created 2/11/24
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Component
@AllArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> userInfo = repository.findByEmail(email);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));

    }
}
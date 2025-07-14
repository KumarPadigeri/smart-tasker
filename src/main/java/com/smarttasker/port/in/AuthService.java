package com.smarttasker.port.in;


import com.smarttasker.dto.auth.LoginRequest;
import com.smarttasker.dto.auth.RegisterRequest;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */

public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest request);

}

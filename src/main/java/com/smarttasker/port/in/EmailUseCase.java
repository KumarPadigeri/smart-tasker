package com.smarttasker.port.in;


/*
 * @Created 7/6/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */public interface EmailUseCase {
    void sendWelcomeEmail(String toEmail, String name);
}

package com.smarttasker.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * @Created 7/5/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {

    private Auth auth = new Auth();
    private JWT jwt = new JWT();


    @Data
    public static class Roles {

        private User user;
        private Admin admin;


    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class User extends Creds {


    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Admin extends Creds {


    }

    @Data
    public static class Creds {
        private String username;
        private String password;

    }

    @Data

    public static class Auth {
        private Roles roles;
    }

    @Data
    public static class JWT {
        private String secret;
    }
}

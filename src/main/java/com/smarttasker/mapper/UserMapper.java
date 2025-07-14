package com.smarttasker.mapper;

import com.smarttasker.domain.model.Users;
import com.test.model.UserDetailsAO;
import org.mapstruct.Mapper;

/*
 * @Created 2/11/24
 * @Project smart-tasker
 * @User Kumar Padigeri
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    Users userDtoToEntity(UserDetailsAO user);
}

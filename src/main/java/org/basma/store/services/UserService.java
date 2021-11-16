package org.basma.store.services;

import java.util.List;
 
import org.basma.store.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String id, UserDto userDto);

	void deleteUser(String userId);
 
	List<UserDto> getAllUsers();
}
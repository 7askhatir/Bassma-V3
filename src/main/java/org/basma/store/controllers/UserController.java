package org.basma.store.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.basma.store.requests.UserRequest; 
import org.basma.store.responses.UserResponse;
import org.basma.store.services.UserService; 
import org.basma.store.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userDto, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<UserResponse>>getUsers(){
		List<UserDto> users = userService.getAllUsers();
		Type listType = new TypeToken <List<UserResponse>>() {}.getType();
		List<UserResponse> usersResponse = new ModelMapper().map(users, listType);
		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);

	}
	

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws Exception {

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);

		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(createUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {

		userService.deleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

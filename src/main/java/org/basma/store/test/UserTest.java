package org.basma.store.test;

import org.basma.store.services.UserService;
import org.basma.store.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class UserTest {

	@Autowired
	UserService userService;

	@Test
	public void testaddUser() {
		
		UserDto userDto = new UserDto("admin", "admin", "admin@email.com", "admin", "admin");
		userService.createUser(userDto);
		System.out.print(userDto.toString());
	}

}

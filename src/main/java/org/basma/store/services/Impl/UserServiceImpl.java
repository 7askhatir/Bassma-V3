package org.basma.store.services.Impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.basma.store.entities.ProductEntity;
import org.basma.store.entities.UserEntity;
import org.basma.store.repositories.UserRepository;
import org.basma.store.services.UserService;
import org.basma.store.shared.Utils;
import org.basma.store.shared.dto.ProductDto;
import org.basma.store.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity userEntity = new UserEntity();

		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("User Alrady Exists !");

		BeanUtils.copyProperties(user, userEntity);

		userEntity.setEncyptePassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userEntity.setUserId(util.generateStringId(32));

		UserEntity newUser = userRepository.save(userEntity);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);

		return userDto;
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncyptePassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		userEntity.setPrenomUser(userDto.getPrenomUser());
		userEntity.setNomUser(userDto.getNomUser());

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();

		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	@Override
	public void deleteUser(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId); 
		
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> users = (List<UserEntity>)userRepository.findAll();
		Type listType = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> usersDto = new ModelMapper().map(users, listType);
		return usersDto;
	}


}

package com.blog.app.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.Entities.User;
import com.blog.app.Exceptions.ResourceNotFoundException;
import com.blog.app.Repository.UserRepo;
import com.blog.app.Services.UserService;
import com.blog.app.payloads.UserDto;

import net.bytebuddy.asm.Advice.This;
@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
	    
		User updatedUser =this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> allUsers=this.userRepo.findAll();
		
		List<UserDto> userDtos=allUsers.stream()
				.map(user-> this.userToDto(user))
				.collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		return user;
	}
	public UserDto userToDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
}

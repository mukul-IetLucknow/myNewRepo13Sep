package com.blog.app.Services;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;

import com.blog.app.payloads.UserDto;

import java.util.List;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}

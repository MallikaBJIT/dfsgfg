package com.example.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.user.dao.UserDao;
import com.example.user.dto.UserRequestDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.entity.User;
import com.example.user.exception.CustomException;

@Service
public class UserService {
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	// Create New User
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		User user = User.builder().name(userRequestDto.getName()).email(userRequestDto.getEmail()).build();

		var response = userDao.save(user);

		return UserResponseDto.builder().id(response.getId()).name(response.getName()).email(response.getEmail())
				.build();
	}

	// Get All Users
	public List<UserResponseDto> getAllUser() {
		List<User> user = userDao.findAll();
		return user.stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// Get user By User ID
	public UserResponseDto getByUser(int userId) {
		User user = userDao.findById(userId)
				.orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
		return mapToDto(user);
	}

	private UserResponseDto mapToDto(User user) {
		return UserResponseDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
	}
}

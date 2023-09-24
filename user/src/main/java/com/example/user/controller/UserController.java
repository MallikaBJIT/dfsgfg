package com.example.user.controller;

import com.example.user.dto.UserRequestDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.entity.User;
import com.example.user.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
		var response = userService.createUser(requestDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		List<UserResponseDto> response = userService.getAllUser();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable int userId) {
		return ResponseEntity.ok(userService.getByUser(userId));
	}
}

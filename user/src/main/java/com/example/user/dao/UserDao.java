package com.example.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}

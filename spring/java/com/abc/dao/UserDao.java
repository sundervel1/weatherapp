package com.abc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.dto.UserDetails;

public interface UserDao extends JpaRepository<UserDetails, String>{

}

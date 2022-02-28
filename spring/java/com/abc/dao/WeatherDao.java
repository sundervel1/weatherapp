package com.abc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.dto.UserDetails;

@Repository
public interface WeatherDao extends JpaRepository<UserDetails, String>{

}

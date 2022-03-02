package com.abc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.dto.UserDetails;
import com.abc.dto.Weather;

@Repository
public interface WeatherDao extends JpaRepository<Weather, String>{
	@Query("from Weather where username=:uname")
	List<Weather> findByUsername(@Param("uname") String username);

}

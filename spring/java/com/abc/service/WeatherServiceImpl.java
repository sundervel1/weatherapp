package com.abc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.WeatherDao;
import com.abc.dto.UserDetails;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	WeatherDao wDao;

	public UserDetails login(UserDetails userDetails) {
		Optional<UserDetails> opt = wDao.findById(userDetails.getUsername());
		if (opt.isPresent()) {
			UserDetails uDetails = opt.get();
			if(uDetails.getPassword().equals(userDetails.getPassword())) {
				return uDetails;
			}
		}
		return null;
	}

}

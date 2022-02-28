package com.abc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.UserDao;
import com.abc.dao.WeatherDao;
import com.abc.dto.UserDetails;
import com.abc.dto.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	WeatherDao wDao;
	@Autowired
	UserDao uDao;

	public UserDetails login(UserDetails userDetails) {
		Optional<UserDetails> opt = uDao.findById(userDetails.getUsername());
		if (opt.isPresent()) {
			UserDetails uDetails = opt.get();
			if(uDetails.getPassword().equals(userDetails.getPassword())) {
				return uDetails;
			}
		}
		return null;
	}

	public Weather add(Weather weather) {
		Weather wthr = wDao.save(weather);
		return wthr;
	}

	public List<Weather> getFavorites(String username) {
		List<Weather> list = wDao.findByUsername(username);
		return list;
	}

}

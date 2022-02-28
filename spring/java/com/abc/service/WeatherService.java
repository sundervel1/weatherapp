package com.abc.service;

import java.util.List;

import com.abc.dto.UserDetails;
import com.abc.dto.Weather;

public interface WeatherService {

	UserDetails login(UserDetails userDetails);

	Weather add(Weather weather);

	List<Weather> getFavorites(String username);

}

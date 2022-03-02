package com.abc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.abc.dto.Image;
import com.abc.dto.ImageUploadResponse;
import com.abc.dto.UserDetails;
import com.abc.dto.Weather;

public interface WeatherService {

	UserDetails login(UserDetails userDetails);

	Weather add(Weather weather);

	List<Weather> getFavorites(String username);

	UserDetails register(UserDetails uDetails);

	ImageUploadResponse upLoadImage(MultipartFile file) throws IOException;

	Image getImage(String name);

}

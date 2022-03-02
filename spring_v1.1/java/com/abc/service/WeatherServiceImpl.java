package com.abc.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abc.dao.ImageDao;
import com.abc.dao.UserDao;
import com.abc.dao.WeatherDao;
import com.abc.dto.Image;
import com.abc.dto.ImageUploadResponse;
import com.abc.dto.UserDetails;
import com.abc.dto.Weather;
import com.abc.util.ImageUtility;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	WeatherDao wDao;
	@Autowired
	UserDao uDao;
	@Autowired
	ImageDao iDao;

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

	public UserDetails register(UserDetails uDetails) {
		UserDetails userSaved = uDao.save(uDetails);
		return userSaved;
	}

	public ImageUploadResponse upLoadImage(MultipartFile file) throws IOException {
//		iDao.save(Image.builder().name(file.getOriginalFilename())
//				.type(file.getContentType())
//				.image(ImageUtility.compressImage(file.getBytes())).build());
		Path root = Paths.get("uploads");
		Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
		return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());
	}

	@Override
	public Image getImage(String name) {
		Optional<Image> opt = iDao.findByName(name);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

}

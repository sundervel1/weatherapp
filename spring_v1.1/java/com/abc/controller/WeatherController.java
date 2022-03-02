package com.abc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abc.dto.Image;
import com.abc.dto.ImageUploadResponse;
import com.abc.dto.UserDetails;
import com.abc.dto.Weather;
import com.abc.service.WeatherService;
import com.abc.util.ImageUtility;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {
	
	@Autowired
	private WeatherService wService;
	
	@PostMapping("/login")
	public String login(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Path root = Paths.get("uploads");
		System.out.println("root: "+root.toAbsolutePath());
		String message="";
		System.out.println("login:"+userDetails);
		String uname = (String) session.getAttribute("user");
		System.out.println("session uname: "+uname);
		UserDetails user = wService.login(userDetails);
		System.out.println("got user: " + user);
		if(user!=null) {
		   session.setAttribute("user", userDetails.getUsername());
		   message = "Hi "+ userDetails.getUsername() +", You have successfully logged in to the system";
		}
		else {
			message = "Authentication failed for user "+userDetails.getUsername();
		}
		return 	message;	
	}
	
	@PostMapping("/logout")
	public String logout(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		System.out.println("logging out: "+userDetails);
		HttpSession session = request.getSession();
		Enumeration<String> attributes = session.getAttributeNames();
		String message = "Hi "+ userDetails.getUsername() +", You have not logged in";
		while (attributes.hasMoreElements()) {
			System.out.println("session has elements");
			String attribute = attributes.nextElement();
			String uName = (String) session.getAttribute(attribute);
			System.out.println(attribute +" - "+uName);
			if(uName.equals(userDetails.getUsername())) {
				System.out.println("invalidating session..."+uName);
				message = "Hi "+ userDetails.getUsername() +", You have successfully logged out of the system";
				session.invalidate();
			}
		}
		return message;		
	}
	@PostMapping("/add")
	public Weather add(@RequestBody Weather weather, HttpServletRequest request) {
		System.out.println(" got " + weather);
		Weather weatherSaved = wService.add(weather);
		return weatherSaved;
	}
	@GetMapping("/getall/{uname}")
	public List<Weather> getFavorites(@PathVariable("uname") String username, HttpServletRequest request){
		System.out.println("user: "+username);
		List<Weather> favList = wService.getFavorites(username);
		return favList;		
	}
	@PostMapping("/register")
	public UserDetails register(@RequestBody UserDetails uDetails, HttpServletRequest request) {
		System.out.println("register: "+uDetails);
		UserDetails userSaved = wService.register(uDetails);
		return userSaved;
	}
	@PostMapping("/upload")
	public ImageUploadResponse upLoadImage(@RequestParam("image") MultipartFile file) throws IOException {
//		Path root = Paths.get("uploads");
//		Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
		ImageUploadResponse iuResponse = wService.upLoadImage(file);
		return iuResponse;
	}
	@GetMapping("/getimage/{name}")
	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name){
		final Image dbImage = wService.getImage(name);
		if(dbImage!=null) {
			return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.getType()))
			.body(ImageUtility.decompressImage(dbImage.getImage()));
		}
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		
	}
    @GetMapping(path = {"/getimage/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {
    	final Image dbImage = wService.getImage(name);

        return Image.builder()
                .name(dbImage.getName())
                .type(dbImage.getType())
                .image(ImageUtility.decompressImage(dbImage.getImage())).build();
    }
    private Resource load(String filename) {
    	Resource resource = null;
    	try {
    	Path root = Paths.get("uploads");
    	Path file = root.resolve(filename);
    	resource = new UrlResource(file.toUri());
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
    }
    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
      System.out.println("get file");
    	Resource file = load(filename);
    	System.out.println(".. got file: "+file.getFilename());
    	ResponseEntity<Resource> resEntity = ResponseEntity.ok()
    	          .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" 
    	                  + file.getFilename() + "\"")
    	          .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
    	          .body(file);
//        .body(file);
    	System.out.println(resEntity);
      return resEntity;
    }
}

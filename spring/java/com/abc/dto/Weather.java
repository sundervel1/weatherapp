package com.abc.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Weather {
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private double lat;
	private double lon;
	private String main;
	private String name;
	private String country;
	public Weather() {
	}
	public Weather(int id, String username, double lat, double lon, String main, String name, String country) {
		this.id = id;
		this.username = username;
		this.lat = lat;
		this.lon = lon;
		this.main = main;
		this.name = name;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Weather [id=" + id + ", username=" + username + ", lat=" + lat + ", lon=" + lon + ", main=" + main
				+ ", name=" + name + ", country=" + country + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}

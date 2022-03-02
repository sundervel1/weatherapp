package com.abc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "w_image")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Image {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String type;
	@Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;
	
}

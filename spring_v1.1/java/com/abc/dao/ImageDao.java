package com.abc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.dto.Image;

public interface ImageDao extends JpaRepository<Image, Long>{
	Optional<Image> findByName(String name);
}

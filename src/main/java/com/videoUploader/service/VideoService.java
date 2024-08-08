package com.videoUploader.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.videoUploader.entities.Video;


public interface VideoService {

	public Video save(Video video,MultipartFile file);
	
	public Video get(String videoId);
	
	public Video getByTitle(String videoTitle);
	
	public List<Video> getAllVideo();

}

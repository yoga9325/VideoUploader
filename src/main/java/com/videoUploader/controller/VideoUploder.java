package com.videoUploader.controller;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videoUploader.entities.Video;
import com.videoUploader.payload.CustomMessage;
import com.videoUploader.service.VideoService;

import lombok.Builder;

@RestController
@RequestMapping("/api")
public class VideoUploder {

	@Autowired
	private VideoService videoService;

	@PostMapping("/video")
	public ResponseEntity<?> getVideo(@RequestParam("file")MultipartFile file,
			@RequestParam("title")String title,
			@RequestParam("description")String descripation)
	{
		Video video=new Video();
		video.setTitle(title);
		video.setDescripation(descripation);
		video.setContentType(file.getContentType());
		video.setVideoId(UUID.randomUUID().toString());
		Video saveVideoService= videoService.save(video, file);
		
		if(saveVideoService != null) 
		{
		return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(title, "Video uploaded successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(CustomMessage.builder().title(title).message("video is not uploaded"));
		}
	}

	@GetMapping("/test")
	public String test() {

		return "Api testing successfully";
	}
}

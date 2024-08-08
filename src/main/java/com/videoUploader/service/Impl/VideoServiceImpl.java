package com.videoUploader.service.Impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.videoUploader.entities.Video;
import com.videoUploader.repository.VideoRepository;
import com.videoUploader.service.VideoService;

import jakarta.annotation.PostConstruct;


@Service
public class VideoServiceImpl implements VideoService {

	@Value("${files.video}")
	public String folderPath;
	
	
	private VideoRepository videoRepository;
	
	
	
	public VideoServiceImpl(VideoRepository videoRepository) {
		super();
		this.videoRepository = videoRepository;
	}

	@PostConstruct
	public void init()
	{
		File file = new File(folderPath);
		
		if(file.exists()== false)
		{
			file.mkdir();
			System.out.println("folder create successfully");
		}
	}
	
	@Override
	public Video save(Video video, MultipartFile file) {
		
		
		try {
			String fileName=file.getOriginalFilename();
			String contype=file.getContentType();
			InputStream inputStream=file.getInputStream();
		
		
			System.out.println(contype);
		
		//folder path : create
		
		
		String cleanFileName= StringUtils.cleanPath(fileName);
		String cleanFolder= StringUtils.cleanPath(folderPath);
		
		//folder path with fileName
		
		Path path=Paths.get(cleanFolder,cleanFileName);
		System.out.println(path);
		
		//copy file to the folder
		Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
		
		//video metadata 
		video.setContentType(contype);
		video.setTitle(cleanFileName);
		video.setFilPath(path.toString());
		
		//save metadata
		return videoRepository.save(video);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Video get(String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video getByTitle(String videoTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAllVideo() {
		// TODO Auto-generated method stub
		return null;
	}

}

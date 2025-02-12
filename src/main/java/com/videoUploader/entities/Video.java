package com.videoUploader.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VideoUploader")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {

	@Id
	private String videoId;
	private String title;
	private String contentType;
	private String descripation;
	private String filPath;
	
}
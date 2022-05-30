package com.ssafy.happyhouse.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.exception.FileException;
import com.ssafy.happyhouse.model.FileInfoDto;

@Component
public class FileUtils {
	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	
	private final String uploadPath = Paths.get("C:", "develop", "upload", today).toString();
	
	private final String getRandoString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public List<FileInfoDto> uploadFiles(MultipartFile[] files, int boardId){
		
		List<FileInfoDto> fileList = new ArrayList<>();
		
		File dir = new File(uploadPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file : files) {
			if(file.getSize() < 1)	continue;
			try {
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				
				final String saveName = getRandoString() + "." + extension;
				
				File target = new File(uploadPath, saveName);
				file.transferTo(target);
				
				FileInfoDto fileInfoDto = new FileInfoDto();
				fileInfoDto.setBoardId(boardId);
				fileInfoDto.setOriginName(file.getOriginalFilename());
				fileInfoDto.setSaveName(saveName);
				fileInfoDto.setFileSize(file.getSize());
				
				fileList.add(fileInfoDto);
			} catch(IOException e) {
				throw new FileException("[" + file.getOriginalFilename() + "] failed to save file...");
			} catch(Exception e) {
				throw new FileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}
		}
		return fileList;
	}
}

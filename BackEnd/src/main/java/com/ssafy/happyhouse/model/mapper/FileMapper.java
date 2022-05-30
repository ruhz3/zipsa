package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.FileInfoDto;

@Mapper
public interface FileMapper {
	public List<FileInfoDto> selectFileListByBoardId(int boardId);
	public FileInfoDto selectFileDetail(int fileId);
	public boolean insertFile(List<FileInfoDto> fileInfoDto);
	public boolean deleteFile(int boardId);
	public int selectFileTotalCount(int boardId);
}

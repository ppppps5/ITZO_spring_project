package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.BoardDto;
import com.itzo.vo.PageMaker;

public interface BoardDao {

	public List<BoardDto> listSearchCriteria(@Param("eseq")int eseq, @Param("pm")PageMaker pm) throws Exception;
	public int listSearchCount(@Param("eseq")int eseq, @Param("pm")PageMaker pm ) throws Exception;
	
	public List<BoardDto> content(@Param("eseq") int eseq, @Param("bseq") int bseq) throws Exception;
	
	public void update(@Param("bTitle") String bTitle, @Param("bContent") String bContent,
								@Param("bseq") int bseq, @Param("eseq") int eseq) throws Exception;
	
	public void seq() throws Exception;
	
	public void write(BoardDto dto) throws Exception;

	public void write2(@Param("eseq") int eseq, @Param("bWriter") String bWriter,
			@Param("bTitle") String bTitle, @Param("bContent") String bContent, 
			@Param("fullName") String fullName) throws Exception;

	public void delete(@Param("bseq") int bseq, @Param("eseq") int eseq) throws Exception;
	
	public List<String> getFiles(int bseq) throws Exception;

	public void deleteFile(@Param("fullName") String fullName) throws Exception;
	public void addFile(@Param("fullName") String fullName) throws Exception;
	public void deleteFileAll(@Param("bseq") int bseq) throws Exception;
	
	public List<BoardDto> mainBoardlist(int eseq) throws Exception;

}
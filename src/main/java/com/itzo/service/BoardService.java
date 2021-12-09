package com.itzo.service;

import java.util.List;

import com.itzo.dto.BoardDto;
import com.itzo.dto.MailDto;
import com.itzo.vo.PageMaker;

public interface BoardService {
	public List<BoardDto> listSearchCriteria(int eseq, PageMaker pm) throws Exception;
	public int listSearchCount(int eseq, PageMaker pm) throws Exception;
	
	public List<BoardDto> content(int eseq, int bseq) throws Exception;
	
	public void update(String bTitle, String bContent, int bseq,int eseq) throws Exception;
	
	public void seq() throws  Exception;
	
	public void write(BoardDto dto) throws Exception;
	
	public void write2(int eseq, String bWriter, 
			String bTitle,String bContent,
			String fullName) throws Exception;

	public void delete(int bseq, int eseq) throws Exception;

	public List<String> getFiles(int bseq)throws Exception;

	public void deleteFile(String fullName)throws Exception;
	public void addFile(String fullName)throws Exception;
	public void deleteFileAll(int bseq)throws Exception;
	
	public List<BoardDto> mainBoardlist(int eseq) throws Exception;
}
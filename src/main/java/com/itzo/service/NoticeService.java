package com.itzo.service;

import java.util.List;

import com.itzo.dto.BoardDto;
import com.itzo.dto.NoticeDto;
import com.itzo.vo.PageMaker;

public interface NoticeService {
	public void write(NoticeDto notice) throws Exception;
	
	public void write2(String nWriter, String nTitle,String nContent,
			String fullName) throws Exception;

	public NoticeDto read(int nseq) throws Exception;

	public void update(NoticeDto notice) throws Exception;

	public void delete(int nseq) throws Exception;

	public List<NoticeDto> listSearchCriteria(PageMaker pm) throws Exception;

	public int listSearchCount(PageMaker pm) throws Exception;
	
	public void seq() throws  Exception;

	public List<String> getFiles(int nseq)throws Exception;

	public void deleteFile(String fullName)throws Exception;

	public void addFile(String fullName)throws Exception;
	
	public void deleteFileAll(int nseq)throws Exception;
	
	public List<NoticeDto> mainNoticelist(int eseq) throws Exception;
}
package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.NoticeDto;
import com.itzo.vo.PageMaker;

public interface NoticeDao {

	public void write(NoticeDto notice) throws Exception;

	public void write2(@Param("nWriter") String nWriter, @Param("nTitle") String nTitle,
			@Param("nContent") String nContent, @Param("fullName") String fullName) throws Exception;

	public NoticeDto read(int nseq) throws Exception;

	public void update(NoticeDto notice) throws Exception;

	public void delete(int nseq) throws Exception;

	public List<NoticeDto> listSearchCriteria(PageMaker pm) throws Exception;

	public int listSearchCount(PageMaker pm) throws Exception;
	
	public void seq() throws Exception;

	public List<String> getFiles(int nseq) throws Exception;

	public void deleteFile(@Param("fullName") String fullName) throws Exception;

	public void addFile(@Param("fullName") String fullName) throws Exception;

	public void deleteFileAll(@Param("nseq") int nseq) throws Exception;
	
	public List<NoticeDto> mainNoticelist(int eseq) throws Exception;
}
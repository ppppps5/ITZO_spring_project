package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.MailDto;
import com.itzo.vo.PageMaker;


public interface MailDao {
	//받은메일
	public List<MailDto> selectReceivelist(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	public int ReceiveCount(@Param("eseq")int eseq,@Param("pm")PageMaker pm)throws Exception;
	//보낸메일
	public List<MailDto> selectSendlist(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	public int SendCount(@Param("eseq")int eseq,@Param("pm")PageMaker pm)throws Exception;
	//삭제메일
	public List<MailDto> selectDeletelist(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	public int DeleteCount(@Param("eseq")int eseq,@Param("pm")PageMaker pm)throws Exception;
	//중요메일
	public List<MailDto> selectImportantlist(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	public int ImportantCount(@Param("eseq")int eseq,@Param("pm")PageMaker pm)throws Exception;
	
	public List<MailDto> selectContent(@Param("eseq") int eseq,@Param("mseq") int mseq) throws Exception;
	public List<MailDto> reply(@Param("eseq") int eseq,@Param("trans") String trans,@Param("mseq") int mseq) throws Exception;
	
	public void seq() throws  Exception;
	
	public void mailwrite(MailDto dto) throws Exception;

	public void mailwrite2(@Param("eseq")int eseq,@Param("receiver") String receiver,
			@Param("title") String title,@Param("content") String content,
			@Param("fullName")String fullName) throws Exception;
	
	
	public void maildelete(@Param("mseq")int mseq,@Param("eseq")int eseq) throws Exception;
	public void realdelete(@Param("mseq")int mseq,@Param("eseq")int eseq) throws Exception;
	
	
	public void starOn(@Param("mseq") int mseq,@Param("imp") String imp) throws Exception;
	public void starOff(@Param("mseq") int mseq,@Param("imp") String imp) throws Exception;

	
	public List<String> getFiles(int mseq)throws Exception;

	public void deleteFile(@Param("fullName")String fullName)throws Exception;
	public void addFile(@Param("fullName")String fullName)throws Exception;
	public void deleteFileAll(@Param("mseq") int mseq)throws Exception;
	
	
	public void addAddress(@Param("eseq") int eseq,@Param("sender") String sender)throws Exception;
	public List<MailDto> selectAddressBook(int eseq)throws Exception;
	public List<MailDto> gomail(@Param("eseq") int eseq,@Param("mseq") int mseq)throws Exception;
	
	
	
}

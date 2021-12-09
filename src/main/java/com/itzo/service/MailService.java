package com.itzo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.MailDto;
import com.itzo.vo.PageMaker;


public interface MailService {
	//받은메일
	public List<MailDto> selectReceivelist(int eseq,PageMaker pm) throws Exception;
	public int ReceiveCount(int eseq,PageMaker pm)throws Exception;
	//보낸메일
	public List<MailDto> selectSendlist(int eseq,PageMaker pm) throws Exception;
	public int SendCount(int eseq,PageMaker pm)throws Exception;
	//삭제메일
	public List<MailDto> selectDeletelist(int eseq,PageMaker pm) throws Exception;
	public int DeleteCount(int eseq,PageMaker pm)throws Exception;
	//중요메일
	public List<MailDto> selectImportantlist(int eseq,PageMaker pm) throws Exception;
	public int ImportantCount(int eseq,PageMaker pm)throws Exception;
	
	public List<MailDto> selectContent( int eseq,int mseq ) throws Exception;
	public List<MailDto> reply(int eseq,String trans,int mseq) throws Exception;
	
	public void seq() throws  Exception;
	
	public void mailwrite(MailDto dto) throws Exception;

	public void mailwrite2(int eseq,String receiver,
			String title,String content,
			String fullName) throws Exception;
	
	
	public void maildelete(int mseq,int eseq) throws Exception;
	public void realdelete(int mseq,int eseq) throws Exception;
	
	
	public void starOn(int mseq,String imp) throws Exception;
	public void starOff(int mseq, String imp) throws Exception;

	
	public List<String> getFiles(int mseq)throws Exception;

	public void deleteFile(String fullName)throws Exception;
	public void addFile(String fullName)throws Exception;
	public void deleteFileAll(int mseq)throws Exception;
	
	
	public void addAddress(int eseq,String sender)throws Exception;
	public List<MailDto> selectAddressBook(int eseq)throws Exception;
	public List<MailDto> gomail(int eseq,int mseq)throws Exception;
	

}

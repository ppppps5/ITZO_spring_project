package com.itzo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.ApproveDto;
import com.itzo.dto.VacationDto;

public interface VacationService {
	// 휴가신청현황 확인
	public List<VacationDto> VacationCheck(int eseq) throws Exception;

	// 휴가신청서 작성
	public void VacationWrite(VacationDto dto, @Param("eseq") int eseq) throws Exception;
	public void VacationWrite2(@Param("eseq") int eseq,@Param("vkseq") int vkseq,@Param("title") String title,@Param("content") String content,
			@Param("approval") String approval,@Param("fullName")String fullName) throws Exception;
	
	//휴가신청 수정
	public void VacationUpdate(@Param("vkseq") int vkseq, @Param("title") String title,@Param("content") String content,@Param("vseq") int vseq, @Param("eseq") int eseq)throws Exception; 

	// 휴가신청서 삭제
	public void VacationDelete(@Param("vseq") int vseq, @Param("eseq") int eseq) throws Exception; 
	
	// 결재내용
	public List<VacationDto> selectContent(@Param("vseq") int vseq, @Param("eseq") int eseq) throws Exception;

	// 파일첨부
	public List<String> getFiles(int vseq) throws Exception;
	public void deleteFile(@Param("fullName") String fullName) throws Exception;
	public void addFile(@Param("fullName") String fullName) throws Exception;
	public void deleteFileAll(@Param("vseq") int vseq) throws Exception;
}

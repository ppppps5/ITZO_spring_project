package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.ApproveDto;
import com.itzo.dto.MailDto;
import com.itzo.vo.PageMaker;


public interface ApproveDao {
	// 결재거절(기결함)
	public List<ApproveDto> ApproveCancle(@Param("eseq") int eseq, @Param("pm")PageMaker pm) throws Exception;
	public int CancleCount(@Param("eseq") int eseq, @Param("pm")PageMaker pm)throws Exception;
	//기결함 결재 삭제
	public void cancledelete(@Param("aseq") int aseq,@Param("eseq") int eseq)throws Exception;
	// 결재요청(미결함)
	public List<ApproveDto> ApproveDecide(@Param("eseq") int eseq, @Param("pm")PageMaker pm) throws Exception;
	public int DecideCount(@Param("eseq") int eseq, @Param("pm")PageMaker pm)throws Exception;	
	//미결함 결재 수정
	public void decideupdate(@Param("title") String title,@Param("content") String content,@Param("aseq") int aseq, @Param("eseq") int eseq)throws Exception; 
	// 결재개인문서함
	public List<ApproveDto> ApproveEach(@Param("eseq") int eseq, @Param("pm")PageMaker pm) throws Exception;
	public int EachCount(@Param("eseq") int eseq, @Param("pm")PageMaker pm)throws Exception;
	// 결재작성(기안문작성)
	public void ApproveWrite(ApproveDto dto,@Param("eseq") int eseq) throws Exception;
	public void ApproveWrite2(@Param("eseq") int eseq,@Param("title") String title,@Param("content") String content,
			@Param("approval") String approval,@Param("fullName")String fullName) throws Exception;
	public void ApproveDelete(@Param("aseq") int aseq, @Param("eseq") int eseq) throws Exception;
	// 팀별결재현황(공통)
	public List<ApproveDto> Approve(@Param("eseq") int eseq) throws Exception;
	// 팀별결재현황(개인)
	public List<ApproveDto> Approvem(@Param("eseq") int eseq) throws Exception;
	//별
	public void starOn(@Param("aseq") int aseq,@Param("myapprove") String myapprove) throws Exception;
	public void starOff(@Param("aseq") int aseq,@Param("myapprove") String myapprove) throws Exception;
	//결재내용
	public List<ApproveDto> selectContent(@Param("aseq") int aseq,@Param("eseq") int eseq) throws Exception;
	
	//파일첨부
	public List<String> getFiles(int aseq)throws Exception;		
	public void deleteFile(@Param("fullName")String fullName)throws Exception;
	public void addFile(@Param("fullName")String fullName)throws Exception;
	public void deleteFileAll(@Param("aseq") int aseq)throws Exception;
}

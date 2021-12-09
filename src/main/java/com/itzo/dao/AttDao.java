package com.itzo.dao;

import java.util.List;

import com.itzo.dto.AttConDto;
import com.itzo.dto.AttDto;

public interface AttDao {
	public List<AttDto> selectAttList(int eseq) throws Exception;
	public List<AttConDto> selectAttConList(int eseq) throws Exception;
	
	public void attCome(AttDto dto) throws Exception;	//출근 insert
	public void attLeave(int eseq) throws Exception; //퇴근 update
}

package com.itzo.service;

import java.util.List;

import com.itzo.dto.AttConDto;
import com.itzo.dto.AttDto;

public interface AttService {
	public List<AttDto> selectAttList(int eseq) throws Exception;
	public List<AttConDto> selectAttConList(int eseq) throws Exception;
	
	public void attCome(AttDto dto) throws Exception;
	public void attLeave(int eseq) throws Exception;
}

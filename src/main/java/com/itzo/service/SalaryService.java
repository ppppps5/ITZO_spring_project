package com.itzo.service;

import java.util.List;

import com.itzo.dto.SalaryDto;
import com.itzo.vo.PageMaker;

public interface SalaryService {
	public List<SalaryDto> selectSalarylist(int eseq, PageMaker pm) throws Exception;
	public int SalaryCount(int eseq, PageMaker pm)throws Exception;
	
	public List<SalaryDto> selectSalaryContent(int eseq, int sseq) throws Exception;
	
}
package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.SalaryDto;
import com.itzo.vo.PageMaker;

public interface SalaryDao {
	public List<SalaryDto> selectSalarylist(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	public int SalaryCount(@Param("eseq")int eseq,@Param("pm")PageMaker pm) throws Exception;
	
	public List<SalaryDto> selectSalaryContent(@Param("eseq") int eseq,@Param("sseq") int sseq) throws Exception;
	
}

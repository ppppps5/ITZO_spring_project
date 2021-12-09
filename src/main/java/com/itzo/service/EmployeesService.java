package com.itzo.service;

import java.util.List;

import com.itzo.dto.EmpImgDto;
import com.itzo.dto.EmployeesDto;

public interface EmployeesService {
	public void insertUser(EmployeesDto empDto) throws Exception;
	public List<EmpImgDto> selectEmpImg(int eseq) throws Exception;
}

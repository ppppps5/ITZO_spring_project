package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.itzo.dto.EmpImgDto;
import com.itzo.dto.EmployeesDto;


public interface EmployeesDao {
	public void insertUser(EmployeesDto empDto) throws Exception;
	public List<EmpImgDto> selectEmpImg(int eseq) throws Exception;
		
	}





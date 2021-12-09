package com.itzo.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.itzo.dao.AttDao;
import com.itzo.dao.EmployeesDao;
import com.itzo.dto.EmpImgDto;
import com.itzo.dto.EmployeesDto;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertUser(EmployeesDto empDto) throws Exception {
		EmployeesDao dao=sqlSession.getMapper(EmployeesDao.class);
		dao.insertUser(empDto);
		
	}

	@Override
	public List<EmpImgDto> selectEmpImg(int eseq) throws Exception{
		EmployeesDao dao=sqlSession.getMapper(EmployeesDao.class);
		return dao.selectEmpImg(eseq);
	}
}

package com.itzo.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itzo.dao.AttDao;
import com.itzo.dto.AttConDto;
import com.itzo.dto.AttDto;


@Service
public class AttServiceImpl implements AttService{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<AttDto> selectAttList(int eseq) throws Exception{
		AttDao dao=sqlSession.getMapper(AttDao.class);
		return dao.selectAttList(eseq);
	}
	
	@Override
	public List<AttConDto> selectAttConList(int eseq) throws Exception{
		AttDao dao=sqlSession.getMapper(AttDao.class);
		return dao.selectAttConList(eseq);
	}
	
	@Override
	public void attCome(AttDto dto) throws Exception {
		AttDao dao=sqlSession.getMapper(AttDao.class);
		dao.attCome(dto);
	}
	
	@Override
	public void attLeave(int eseq) throws Exception {
		AttDao dao=sqlSession.getMapper(AttDao.class);
		dao.attLeave(eseq);
	}
}

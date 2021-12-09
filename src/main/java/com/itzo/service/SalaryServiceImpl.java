package com.itzo.service;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.itzo.dao.SalaryDao;
import com.itzo.dto.SalaryDto;
import com.itzo.vo.PageMaker;

@Service
public class SalaryServiceImpl implements SalaryService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<SalaryDto> selectSalarylist(int eseq, PageMaker pm) throws Exception {
		SalaryDao dao = sqlSession.getMapper(SalaryDao.class);
		
		return dao.selectSalarylist(eseq, pm);
	}

	@Override
	public int SalaryCount(int eseq, PageMaker pm) throws Exception {
		SalaryDao dao = sqlSession.getMapper(SalaryDao.class);
		
		return dao.SalaryCount(eseq, pm);
	}

	@Override
	public List<SalaryDto> selectSalaryContent(int eseq, int sseq) throws Exception {
		SalaryDao dao = sqlSession.getMapper(SalaryDao.class);
		
		return dao.selectSalaryContent(eseq, sseq);
	}

}
package com.itzo.service;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.itzo.dao.VacationDao;
import com.itzo.dto.VacationDto;

@Service
public class VacationServiceImpl implements VacationService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<VacationDto> VacationCheck(int eseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		return dao.VacationCheck(eseq);
	}

	@Override
	public void VacationWrite(VacationDto dto, int vseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.VacationWrite(dto, vseq);
	}

	@Override
	public void VacationWrite2(int eseq, int vkseq,String title, String content, String approval, String fullName)
			throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.VacationWrite2(eseq, vkseq, title, content, approval, fullName);
	}


	@Override
	public void VacationDelete(int vseq, int eseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.VacationDelete(vseq, eseq);
	}

	@Override
	public void VacationUpdate(int vkseq, String title, String content,int vseq, int eseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.VacationUpdate(vkseq, title, content, vseq, eseq );
	}

	@Override
	public List<VacationDto> selectContent(int vseq, int eseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		return dao.selectContent(vseq, eseq);
	}

	@Override
	public List<String> getFiles(int vseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		return dao.getFiles(vseq);
	}

	@Override
	public void deleteFile(String fullName) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.deleteFile(fullName);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.addFile(fullName);
	}

	@Override
	public void deleteFileAll(int vseq) throws Exception {
		VacationDao dao = sqlSession.getMapper(VacationDao.class);
		dao.deleteFileAll(vseq);
	}





	
	
}

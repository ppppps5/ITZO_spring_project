package com.itzo.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itzo.dao.ApproveDao;
import com.itzo.dto.ApproveDto;
import com.itzo.vo.PageMaker;

@Service
public class ApproveServiceImpl implements ApproveService {
	@Inject
	private SqlSession sqlSession;
	

	@Override
	public List<ApproveDto> ApproveCancle(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.ApproveCancle(eseq, pm);
	}

	@Override
	public int CancleCount(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.CancleCount(eseq, pm);
	}
	
	@Override
	public void cancledelete(int aseq, int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.cancledelete(aseq, eseq);
	}


	@Override
	public List<ApproveDto> ApproveDecide(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.ApproveDecide(eseq, pm);
	}

	@Override
	public int DecideCount(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.DecideCount(eseq, pm);
	}

	@Override
	public List<ApproveDto> ApproveEach(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.ApproveEach(eseq, pm);
	}

	@Override
	public int EachCount(int eseq, PageMaker pm) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.EachCount(eseq, pm);
	}

	@Override
	public void ApproveWrite(ApproveDto dto, int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.ApproveWrite(dto, eseq);
	}

	@Override
	public void ApproveWrite2(int eseq,String title, String content,String approval,String fullName) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.ApproveWrite2(eseq, title, content, approval,fullName);
	}
	
	@Override
	public void ApproveDelete(int aseq, int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.ApproveDelete(aseq, eseq);
	}

	@Override
	public List<ApproveDto> Approve(int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.Approve(eseq);
	}
	
	@Override
	public List<ApproveDto> Approvem(int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.Approvem(eseq);
	}

	@Override
	public void starOn(int aseq, String myapprove) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.starOn(aseq,myapprove);
	}

	@Override
	public void starOff(int aseq, String myapprove) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.starOff(aseq,myapprove);
	}

	@Override
	public List<ApproveDto> selectContent(int aseq, int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.selectContent(aseq, eseq);
	}

	@Override
	public void decideupdate(String title, String content, int aseq, int eseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.decideupdate(title, content, aseq, eseq);
	}


	@Override
	public List<String> getFiles(int aseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		return dao.getFiles(aseq);
	}

	@Override
	public void deleteFile(String fullName) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.deleteFile(fullName);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.addFile(fullName);
	}

	@Override
	public void deleteFileAll(int aseq) throws Exception {
		ApproveDao dao=sqlSession.getMapper(ApproveDao.class);
		dao.deleteFileAll(aseq);
	}

	

	
		
}

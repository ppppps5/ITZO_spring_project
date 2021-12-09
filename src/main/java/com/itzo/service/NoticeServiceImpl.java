package com.itzo.service;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.itzo.dao.NoticeDao;
import com.itzo.dto.NoticeDto;
import com.itzo.vo.PageMaker;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public void write(NoticeDto notice) throws Exception {
		System.out.println(sqlSession);
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.write(notice);

	}

	@Override
	public void write2(String bWriter, String bTitle, String bContent, String fullName) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.write2(bWriter, bTitle, bContent, fullName);

	}

	@Override
	public NoticeDto read(int nseq) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);

		return dao.read(nseq);
	}

	@Override
	public void update(NoticeDto notice) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.update(notice);
	}

	@Override
	public void delete(int nseq) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.delete(nseq);

	}

	@Override
	public List<NoticeDto> listSearchCriteria(PageMaker pm) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);

		return dao.listSearchCriteria(pm);
	}

	@Override
	public int listSearchCount(PageMaker pm) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);

		return dao.listSearchCount(pm);
	}

	@Override
	public void seq() throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.seq();
	}

	@Override
	public List<String> getFiles(int nseq) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		return dao.getFiles(nseq);
	}

	@Override
	public void deleteFile(String fullName) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.deleteFile(fullName);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.addFile(fullName);
	}

	@Override
	public void deleteFileAll(int nseq) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		dao.deleteFileAll(nseq);
	}
	
	@Override
	public List<NoticeDto> mainNoticelist(int eseq) throws Exception {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		return dao.mainNoticelist(eseq);
	}
	

}
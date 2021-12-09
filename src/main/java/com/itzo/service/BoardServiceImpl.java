package com.itzo.service;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.itzo.dao.BoardDao;
import com.itzo.dto.BoardDto;
import com.itzo.vo.PageMaker;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BoardDto> listSearchCriteria(int eseq, PageMaker pm) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		return dao.listSearchCriteria(eseq, pm);
	}

	@Override
	public int listSearchCount(int eseq, PageMaker pm) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		return dao.listSearchCount(eseq, pm);
	}

	@Override
	public void write(BoardDto dto) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.write(dto);

	}

	@Override
	public void delete(int bseq, int eseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.delete(bseq, eseq);

	}

	@Override
	public List<BoardDto> content(int eseq, int bseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		return dao.content(eseq, bseq);
	}

	@Override
	public void update(String bTitle, String bContent, int bseq, int eseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		 dao.update(bTitle, bContent, bseq, eseq);
	}

	@Override
	public List<String> getFiles(int bseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		return dao.getFiles(bseq);
	}

	@Override
	public void deleteFile(String fullName) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.deleteFile(fullName);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.addFile(fullName);
	}

	@Override
	public void deleteFileAll(int bseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.deleteFileAll(bseq);
	}

	@Override
	public void seq() throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.seq();
	}

	@Override
	public void write2(int eseq, String bWriter, String bTitle, String bContent, String fullName) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.write2(eseq, bWriter, bTitle, bContent, fullName);

	}

	@Override
	public List<BoardDto> mainBoardlist(int eseq) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		return dao.mainBoardlist(eseq);
	}

}
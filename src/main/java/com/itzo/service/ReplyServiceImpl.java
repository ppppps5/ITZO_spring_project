package com.itzo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itzo.dao.ReplyDao;
import com.itzo.dto.ReplyDto;
import com.itzo.vo.PageMaker;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addReply(ReplyDto dto) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);
		dao.insert(dto);
	}

	@Override
	public List<ReplyDto> listReply(Integer bseq) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);
		return dao.list(bseq);
	}

	@Override
	public void updateReply(ReplyDto dto) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);

		dao.update(dto);
	}

	@Override
	public void deleteReply(Integer rseq) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);
		dao.delete(rseq);
	}

	@Override
	public List<ReplyDto> listReplyPage(Integer bseq, PageMaker cri) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);
		return dao.listPage(bseq, cri);
	}

	@Override
	public int count(Integer bseq) throws Exception {
		ReplyDao dao = sqlSession.getMapper(ReplyDao.class);
		return dao.count(bseq);
	}
	
}
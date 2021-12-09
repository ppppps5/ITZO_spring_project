package com.itzo.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itzo.dao.MailDao;
import com.itzo.dto.MailDto;
import com.itzo.vo.PageMaker;


@Service
public class MailServiceImpl implements MailService{
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<MailDto> selectReceivelist(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectReceivelist(eseq,pm);
	}
	
	@Override
	public int ReceiveCount(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.ReceiveCount(eseq,pm);
	}
	
	@Override
	public List<MailDto> selectSendlist(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectSendlist(eseq,pm);
	}

	@Override
	public int SendCount(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.SendCount(eseq,pm);
	}
	
	@Override
	public void mailwrite(MailDto dto) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.mailwrite(dto);
	}

	@Override
	public void maildelete(int mseq,int eseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.maildelete(mseq,eseq);
	}

	@Override
	public List<MailDto> selectDeletelist(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectDeletelist(eseq,pm);
	}
	
	@Override
	public int DeleteCount(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.DeleteCount(eseq,pm);
	}
	
	@Override
	public void realdelete(int mseq,int eseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.realdelete(mseq,eseq);
	}

	@Override
	public List<MailDto> selectImportantlist(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectImportantlist(eseq,pm);
	}


	@Override
	public int ImportantCount(int eseq,PageMaker pm) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.ImportantCount(eseq,pm);
	}
	
	@Override
	public void starOn(int mseq,String imp) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.starOn(mseq,imp);
	}

	@Override
	public void starOff(int mseq,String imp) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.starOff(mseq,imp);
	}

	@Override
	public List<MailDto> selectContent(int eseq,int mseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectContent(eseq,mseq);
	}

	@Override
	public List<MailDto> reply(int eseq,String trans, int mseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.reply(eseq,trans, mseq );
	}

	@Override
	public List<String> getFiles(int mseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.getFiles(mseq);
	}

	@Override
	public void deleteFile(String fullName) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.deleteFile(fullName);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.addFile(fullName);
	}

	@Override
	public void deleteFileAll(int mseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.deleteFileAll(mseq);
	}

	@Override
	public void addAddress(int eseq,String sender) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.addAddress(eseq,sender);
	}

	@Override
	public List<MailDto> gomail(int mseq, int eseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.gomail(mseq, eseq);
	}

	@Override
	public void seq() throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.seq();
	}

	@Override
	public void mailwrite2(int eseq,String receiver, String title, String content, String fullName) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		dao.mailwrite2(eseq,receiver, title, content, fullName);
		
	}

	@Override
	public List<MailDto> selectAddressBook(int eseq) throws Exception {
		MailDao dao=sqlSession.getMapper(MailDao.class);
		return dao.selectAddressBook(eseq);
	}

	

	





	



	


}

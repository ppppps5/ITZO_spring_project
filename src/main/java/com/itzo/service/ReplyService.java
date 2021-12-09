package com.itzo.service;

import java.util.List;

import com.itzo.dto.ReplyDto;
import com.itzo.vo.PageMaker;

public interface ReplyService {

	public void addReply(ReplyDto dto) throws Exception;

	public List<ReplyDto> listReply(Integer bseq) throws Exception;

	public void updateReply(ReplyDto dto) throws Exception;

	public void deleteReply(Integer rseq) throws Exception;

	public List<ReplyDto> listReplyPage(Integer bseq, PageMaker pm) throws Exception;

	public int count(Integer bseq) throws Exception;

}
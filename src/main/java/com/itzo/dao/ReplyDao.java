package com.itzo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itzo.dto.ReplyDto;
import com.itzo.vo.PageMaker;

public interface ReplyDao {

	public List<ReplyDto> list(Integer bseq) throws Exception;

	public void insert(ReplyDto dto) throws Exception;

	public void update(ReplyDto dto) throws Exception;

	public void delete(Integer rseq) throws Exception;

	public List<ReplyDto> listPage(@Param("bseq") Integer bseq, @Param("pm") PageMaker pm) throws Exception;

	public int count(Integer bseq) throws Exception;

}

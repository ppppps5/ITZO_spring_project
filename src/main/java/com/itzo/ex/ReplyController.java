package com.itzo.ex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itzo.dto.ReplyDto;
import com.itzo.service.ReplyService;
import com.itzo.vo.PageMaker;


@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Inject
	private ReplyService rs;
	
	@RequestMapping(value="/all/{bseq}",method=RequestMethod.GET)
	public ResponseEntity<List<ReplyDto>> listReply(@PathVariable("bseq") Integer bseq){
		ResponseEntity<List<ReplyDto>> entity=null;

	    try {
	     
	    	entity = new ResponseEntity<>(rs.listReply(bseq), HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		return entity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	  public ResponseEntity<String> addReply(@RequestBody ReplyDto dto) {

	    ResponseEntity<String> entity = null;
	    try {
	    	rs.addReply(dto);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{rseq}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	  public ResponseEntity<String> updateReply(@PathVariable("rseq") Integer rseq, @RequestBody ReplyDto dto) {

	    ResponseEntity<String> entity = null;
	    try {
	    	dto.setRseq(rseq);
	      rs.updateReply(dto);

	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{rseq}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> deleteReply(@PathVariable("rseq") Integer rseq) {

	    ResponseEntity<String> entity = null;
	    try {
	      rs.deleteReply(rseq);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{bseq}/{page}", method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> listReplyPage(
	      @PathVariable("bseq") Integer bseq,
	      @PathVariable("page") Integer page) {

	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
	      PageMaker pm = new PageMaker();
	      pm.setPage(page);


	      Map<String, Object> map = new HashMap<String, Object>();
	      List<ReplyDto> list = rs.listReplyPage(bseq, pm);

	      map.put("list", list);

	      int replyCount = rs.count(bseq);
	      pm.setTotalCount(replyCount);

	      map.put("pageMaker", pm);

	      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

}
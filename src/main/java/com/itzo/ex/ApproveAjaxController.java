package com.itzo.ex;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itzo.dto.ApproveDto;
import com.itzo.service.ApproveService;


@RestController
@RequestMapping("/myapprove")
public class ApproveAjaxController {
	@Inject
	private ApproveService as;
	
	@RequestMapping(value = "/{aseq}/{myapprove}", method = { RequestMethod.GET})
	public void star1(@PathVariable("aseq") int aseq,@PathVariable("myapprove") String myapprove) {
		
	}
	//http://localhost:8082/ex/imp/2/n
	@RequestMapping(value = "/{aseq}/{myapprove}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> star(@PathVariable("aseq") int aseq,@PathVariable("myapprove") String myapprove,
			@RequestBody ApproveDto dto) {
		System.out.println("확인합시다~");
	ResponseEntity<String> entity = null;
	try {
		dto.setMyapprove(myapprove);
		dto.setAseq(aseq);;
		System.out.println(myapprove);
		System.out.println(aseq);
		if(myapprove.equals("y")) {
			as.starOff(aseq,myapprove);
		}else {
			as.starOn(aseq,myapprove);
			
		}
		
		

	entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	} catch (Exception e) {
	e.printStackTrace();
	entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	return entity;
	}
}

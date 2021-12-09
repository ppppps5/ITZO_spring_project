package com.itzo.ex;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itzo.dto.MailDto;
import com.itzo.service.MailService;

@RestController
@RequestMapping("/imp")
public class AjaxController {
	@Inject
	private MailService ms;

	@RequestMapping(value = "/{mseq}/{imp}", method = { RequestMethod.GET })
	public void star1(Model model,Locale locale,@PathVariable("mseq") int mseq, @PathVariable("imp") String imp,Principal principal) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		
		System.out.println(user);
	}

	@RequestMapping(value = "/{mseq}/{imp}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> star(Locale locale,@PathVariable("mseq") int mseq, @PathVariable("imp") String imp,
			Principal principal,Model model) {
		System.out.println("확인합시다~");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		
		System.out.println(user);
		
		
		ResponseEntity<String> entity = null;
		try {
			/*
			 * dto.setImp(imp); dto.setMseq(mseq);
			 */
			;
			System.out.println(imp);
			System.out.println(mseq);
			if (imp.equals("y")) {
				ms.starOff(mseq,imp);
			} else {
				ms.starOn(mseq,imp);

			}
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	//주소록 추가
	@RequestMapping(value = "/{eseq}/{sender:.+}", method = { RequestMethod.GET })
	public void add_address(@PathVariable("sender") String sender) {

	}
	
	@RequestMapping(value = "/{eseq}/{sender:.+}", method = RequestMethod.POST)
	public ResponseEntity<String> add_address(@PathVariable("eseq") int eseq,@PathVariable("sender") String sender,
			@RequestBody MailDto dto) {
		System.out.println("확인합시다~");
		ResponseEntity<String> entity = null;
		try {
			dto.setSender(sender);
			;
			System.out.println(sender);
			ms.addAddress(eseq,sender);
			
			
			entity = new ResponseEntity<String>("add", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
}

package com.itzo.ex;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itzo.dto.EmpImgDto;
import com.itzo.service.BoardService;
import com.itzo.service.EmployeesService;
import com.itzo.service.NoticeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private EmployeesService employeesService;
	
	@Inject
	private BoardService bs;
	
	@Inject
	private NoticeService ns;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,Principal principal) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

		
		List<EmpImgDto> list=employeesService.selectEmpImg(user);	//사원 사진,이름,직책 가져오기
		
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		
		
//		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		model.addAttribute("emp",list);
		model.addAttribute("boardlist", bs.mainBoardlist(user));
		model.addAttribute("noticelist", ns.mainNoticelist(user));
		

		return "home";
	}
	
	
}

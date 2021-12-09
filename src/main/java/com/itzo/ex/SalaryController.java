package com.itzo.ex;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itzo.service.SalaryService;
import com.itzo.vo.PageMaker;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/salary/*")
public class SalaryController {

	@Inject
	private SalaryService ss;

	private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */

	// 게시판 리스트
	@RequestMapping(value = "/salary_list", method = RequestMethod.GET)
	public String salarylist(Locale locale, Model model, @RequestParam("eseq") int eseq, PageMaker pm, Principal principal) throws Exception {
		logger.info("");
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
		
		System.out.println(pm);
		model.addAttribute("salarylist", ss.selectSalarylist(eseq, pm));
		System.out.println(ss.selectSalarylist(eseq, pm));
		pm.setTotalCount(ss.SalaryCount(eseq, pm));

		return "salary/salary_list";
	}

	// 급여 상세 페이지
	@RequestMapping(value = "/salary_content", method = RequestMethod.GET)
	public String salarycontent(Locale locale, @RequestParam("sseq") int sseq, @RequestParam("eseq") int eseq, Model model, Principal principal) throws Exception {
		model.addAttribute("sseq",sseq);
		logger.info("");
		
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
		
		model.addAttribute("salarycontent", ss.selectSalaryContent(eseq, sseq));
		System.out.println(ss.selectSalaryContent(eseq, sseq));

		return "salary/salary_content";
	}
	
}
package com.itzo.ex;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itzo.dto.AttConDto;
import com.itzo.dto.AttDto;
import com.itzo.dto.EmpImgDto;
import com.itzo.service.AttService;
import com.itzo.service.EmployeesService;

@Controller
public class AttController {
	
	@Inject
	private AttService attService;
	
	@Inject
	private EmployeesService employeesService;
	
	private static final Logger logger = LoggerFactory.getLogger(AttController.class);
	
	@RequestMapping(value = "/attendance", method = RequestMethod.GET)	//근태관리 조회
	public String Attendance(Model model, @RequestParam("eseq") int eseq) throws Exception{
		
		List<AttDto> list=attService.selectAttList(eseq);
		List<AttConDto> conList=attService.selectAttConList(eseq);
		
		logger.info(list.toString());
		logger.info(conList.toString());
		
		
		model.addAttribute("att",list);
		model.addAttribute("attcon",conList);
	
		return "emp/attendance";
	}
	
	//  출근(insert)
		@RequestMapping(value = "/attcome", method = RequestMethod.GET)	//출근
		public String come(Model model, AttDto dto, @RequestParam("eseq") int eseq, Principal principal) throws Exception {
			logger.info("");
			
			attService.attCome(dto);
			
			String userId=principal.getName();
			int user=Integer.parseInt(userId);
			
			//로그인 사원 정보
			List<EmpImgDto> list=employeesService.selectEmpImg(user);
			model.addAttribute("userName",userId );
			model.addAttribute("emp",list);
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
			Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
			boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
			
			model.addAttribute("admin", admin);
			
			
			return "redirect:/";
		}
	
		//퇴근(update)
		@RequestMapping(value = "/attleave", method = RequestMethod.GET)	//퇴근
		public String leave(Model model, AttDto dto, @RequestParam("eseq") int eseq,Principal principal) throws Exception {
			logger.info("");
			
			attService.attLeave(eseq);
			
			String userId=principal.getName();
			int user=Integer.parseInt(userId);
			
			//로그인 사원 정보
			List<EmpImgDto> list=employeesService.selectEmpImg(user);
			model.addAttribute("userName",userId );
			model.addAttribute("emp",list);
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
			Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
			boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
			
			model.addAttribute("admin", admin);
			
			return "redirect:/";
		}
}

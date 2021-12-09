package com.itzo.ex;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itzo.dao.EmployeesDao;
import com.itzo.dto.EmpImgDto;
import com.itzo.dto.EmployeesDto;
import com.itzo.service.EmployeesService;

@Controller
public class UserController {
	@Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmployeesDao empDao;
	@Inject
	private EmployeesService employeesService;
    
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/user/userForm.do", method = RequestMethod.GET)
	public String userForm(Locale locale, Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
//		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
//		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
//		
//		model.addAttribute("admin", admin);
		
		
		return "user/userForm";
		
	}
	
	@RequestMapping(value = "/user/addUser.do", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute EmployeesDto empDto, Principal principal) throws Exception {
		System.out.println(empDto);
		String encPassword = passwordEncoder.encode(empDto.getPwd());
		empDto.setPwd(encPassword);
        empDao.insertUser(empDto);
        
//    	String userId=principal.getName();
//		int user=Integer.parseInt(userId);
//		
//		//로그인 사원 정보
//		List<EmpImpDto> list=employeesService.selectEmpImp(user);
//		model.addAttribute("userName",userId );
//		model.addAttribute("emp",list);
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
//		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
//		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
//		
//		model.addAttribute("admin", admin);
        
		return "user/registrationOk";
	}
}

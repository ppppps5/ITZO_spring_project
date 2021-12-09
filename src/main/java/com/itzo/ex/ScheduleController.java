package com.itzo.ex;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@RequestMapping(value = "/emp/scheduleCheck.do", method = RequestMethod.GET)
	public String scheduleCheck(Locale locale, Model model) {
		logger.info("일정 확인 페이지");

		return "/emp/scheduleCheck";
	}
	
	@RequestMapping(value = "/emp/schedulePopup", method = RequestMethod.GET)
	public String schedulePopup(Locale locale, Model model) {
		logger.info("일정 추가 페이지");

		return "/emp/schedulePopup";
	}

	/*
	 * @RequestMapping(value = "/emp/test.do", method = RequestMethod.GET) public
	 * String test(Locale locale, Model model) { logger.info("테스트 페이지");
	 * 
	 * return "/emp/test"; }
	 */
	
}



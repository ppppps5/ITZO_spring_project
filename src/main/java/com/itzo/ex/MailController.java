package com.itzo.ex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itzo.dto.MailDto;
import com.itzo.service.MailService;
import com.itzo.vo.PageMaker;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/mail/*")
public class MailController {

	@Inject
	private MailService ms;
	
	@Resource(name="uploadPath")//자원 주입
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */

	// 받은 메일함 리스트
	@RequestMapping(value = "/mail_receivelist", method = RequestMethod.GET)
	public String receivelist(Locale locale,Model model,@RequestParam("eseq") int eseq, PageMaker pm,Principal principal) throws Exception {
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
		model.addAttribute("receivelist", ms.selectReceivelist(eseq,pm));
		System.out.println("확인해봅시다");
		System.out.println(ms.selectReceivelist(eseq,pm));
		pm.setTotalCount(ms.ReceiveCount(eseq,pm));
		
		return "mail/mail_receivelist";
	}

	// 보낸 메일함 리스트
	@RequestMapping(value = "/mail_sendlist", method = RequestMethod.GET)
	public String sendlist(Locale locale,Model model,@RequestParam("eseq") int eseq, PageMaker pm,Principal principal) throws Exception {
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
		model.addAttribute("sendlist", ms.selectSendlist(eseq,pm));
		System.out.println(ms.selectSendlist(eseq,pm));
		pm.setTotalCount(ms.SendCount(eseq,pm));

		return "mail/mail_sendlist";
	}
	
	// 삭제 메일함 리스트
		@RequestMapping(value = "/mail_deletelist", method = RequestMethod.GET)
		public String deletelist(Locale locale,Model model, @RequestParam("eseq") int eseq,PageMaker pm,Principal principal) throws Exception {
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
			model.addAttribute("deletelist", ms.selectDeletelist(eseq,pm));
			System.out.println(ms.selectDeletelist(eseq,pm));
			pm.setTotalCount(ms.DeleteCount(eseq,pm));
			
			return "mail/mail_deletelist";
		}
	
	// 중요 메일함 리스트
	@RequestMapping(value = "/mail_important", method = RequestMethod.GET)
	public String important(Locale locale,Model model,@RequestParam("eseq") int eseq, PageMaker pm,Principal principal) throws Exception {
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
		model.addAttribute("important", ms.selectImportantlist(eseq,pm));
		System.out.println(ms.selectImportantlist(eseq,pm));
		pm.setTotalCount(ms.ImportantCount(eseq,pm));

		return "mail/mail_important";
	}

	
	
	// 메일 내용 (메일 읽어오기)
	@RequestMapping(value = "/mail_content", method = RequestMethod.GET)
	public String content(Locale locale,@RequestParam("mseq") int mseq,@RequestParam("eseq") int eseq, Model model,Principal principal) throws Exception {
		model.addAttribute("mseq",mseq);
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

		
		
		model.addAttribute("content", ms.selectContent(eseq,mseq));
		System.out.println(ms.selectContent(eseq,mseq));

		return "mail/mail_content";
	}
	
	@ResponseBody
	@RequestMapping(value ="/allFile", method = RequestMethod.POST)//해당 글에 대한 업로드 파일 가져오기
	public ResponseEntity<List<String>> allFile(@RequestParam("mseq") int mseq) throws Exception {
		System.out.println("업로드 파일 가져오기1");

		ResponseEntity<List<String>> entity=null;

		System.out.println(mseq);
		
	    try {
	    	entity = new ResponseEntity<>(ms.getFiles(mseq), HttpStatus.OK);
	    	//JSON형태로 처리->RESTFULL사용
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

		return entity;
	}
	
	//메일 내용에서 업로드 된 파일 불러오기
	@ResponseBody
	@RequestMapping(value ="/allFile", method = RequestMethod.GET)//해당 글에 대한 업로드 파일 가져오기
	public ResponseEntity<List<String>> allFile2(@RequestParam("mseq") int mseq) throws Exception {
		System.out.println("업로드 파일 가져오기2");

		ResponseEntity<List<String>> entity=null;

		System.out.println(mseq);
		
	    try {
	    	entity = new ResponseEntity<>(ms.getFiles(mseq), HttpStatus.OK);
	    	//JSON형태로 처리->RESTFULL사용
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

		return entity;
	}

	
	
	
	@ResponseBody
	@RequestMapping("/displayFile")//업로드파일을 어떻게 처리할거냐(보여줄거냐 vs 다운로드할거냐)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		System.out.println("displayFileE: " + fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
			MediaType mType = null;
			if (formatName.equals("JPG")) {
				mType = MediaType.IMAGE_JPEG;
			} else if (formatName.equals("GIF")) {
				mType = MediaType.IMAGE_JPEG;
			} else if (formatName.equals("PNG")) {
				mType = MediaType.IMAGE_JPEG;
			}
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileName);
			if (fileName.contains("s_")) {
				headers.setContentType(mType);
				System.out.println("displayFileE: 2" + fileName);
			} else {
				System.out.println("displayFileE: 3" + fileName);
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	

	// 받은 메일 삭제(delete)
	@RequestMapping(value = "/receivedelete", method = RequestMethod.POST)
	public String delete(Locale locale,@RequestParam("mseq") int mseq, @RequestParam("eseq") int eseq,Model model, RedirectAttributes rttr,Principal principal) throws Exception {
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
		
		ms.maildelete(mseq,eseq);

		rttr.addFlashAttribute("msg", "delete");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/mail/mail_receivelist";
	}

	// 보낸 메일 삭제(delete)
	@RequestMapping(value = "/senddelete", method = RequestMethod.POST)
	public String delete2(Locale locale,@RequestParam("mseq") int mseq, Model model,@RequestParam("eseq") int eseq, RedirectAttributes rttr,Principal principal) throws Exception {
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

		ms.maildelete(mseq,eseq);

		rttr.addFlashAttribute("msg", "delete");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/mail/mail_sendlist";
	}

	// 중요 메일 삭제(delete)
	@RequestMapping(value = "/impdelete", method = RequestMethod.POST)
	public String delete3(Locale locale,@RequestParam("mseq") int mseq,@RequestParam("eseq") int eseq, Model model, RedirectAttributes rttr,Principal principal) throws Exception {
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


		ms.maildelete(mseq,eseq);

		rttr.addFlashAttribute("msg", "delete");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/mail/mail_important";
	}

	// 삭제 메일함 영구삭제
	@RequestMapping(value = "/realdelete", method = RequestMethod.POST)
	public String realdelete(Locale locale,@RequestParam("mseq") int mseq,@RequestParam("eseq") int eseq, Model model, RedirectAttributes rttr,Principal principal) throws Exception {
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

		
		ms.realdelete(mseq,eseq);
		rttr.addFlashAttribute("msg", "realdelete");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/mail/mail_deletelist";
	}



	// 메일 쓰기
	@RequestMapping(value = "/mail_write", method = RequestMethod.GET)
	public void write(Locale locale,@RequestParam("eseq") int eseq, Model model,Principal principal) throws Exception {
		logger.info("");
		
		//로그인(사원번호 가져오기)
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
		//로그인(사원번호 가져오기)
		
		
		model.addAttribute("addressbook",ms.selectAddressBook(eseq));
	}

	

	
	
	// 메일 쓰기(insert)
	@RequestMapping(value = "/mail_write", method = RequestMethod.POST)
	public String writePOST(Locale locale,@RequestParam("eseq")int eseq,@RequestParam("receiver") String receiver,
			@RequestParam("title") String title,@RequestParam("content") String content
			, RedirectAttributes rttr,
			@RequestParam("File") MultipartFile File, Model model,Principal principal) throws Exception {
		logger.info("");
		
		//로그인(사원번호 가져오기)
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
		//로그인(사원번호 가져오기)
		
		
		
		System.out.println("데이터를 찍어본다 : "+receiver+title+content);
		System.out.println("originalName: " + File.getOriginalFilename());

		String savedName = System.currentTimeMillis() + "_" + File.getOriginalFilename();
		// SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMDD_HHMMSS_");
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYMM");
		Calendar now = Calendar.getInstance();//현재시간 얻어옴

		String yearPath = formatter.format(now.getTime());//실제 현재시간을 숫자로 얻어와서yyyymm형태로 저장
		String savedPath = uploadPath + "////" + yearPath;//저장할 파일 경로

		System.out.println(savedPath);
		File makeFolder = new File(savedPath);
		// folderPath의 디렉토리가 존재하지 않을경우 디렉토리 생성.
		if (!makeFolder.exists()) {//폴더 존재여부 확인
			makeFolder.mkdir();
			System.out.println("폴더를 생성합니다.");
		}

		// 파일저장
		File target = new File(savedPath, savedName);//경로,이름 저장
		FileCopyUtils.copy(File.getBytes(), target);//실제 파일에 바이너리 코드를 저장
	

		String formatName = savedName.substring(savedName.lastIndexOf(".") + 1).toUpperCase();//확장자 추출
		System.out.println(formatName);
		String uploadedFileName = null;
		if (formatName.equals("JPG") || formatName.equals("PNG") || formatName.equals("GIF")) {
			uploadedFileName = makeThumbnail(savedPath, yearPath, savedName);//이미지면 썸네일 만듦
		} else {
			uploadedFileName = "////" + yearPath + "////" + savedName;
		}
		System.out.println(uploadedFileName);
		ms.mailwrite2(eseq,receiver,title,content,uploadedFileName);
		//실제 업로드한 파일이름 저장
		rttr.addFlashAttribute("msg", "send");
		
		rttr.addAttribute("eseq", eseq);
		return "redirect:/mail/mail_sendlist";
	}
	
	private static String makeThumbnail(String savedPath, String yearPath, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(savedPath, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumbnailName = savedPath + "////" + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		//뭔지 모르면 찍어보기
		System.out.println("yearPath= "+yearPath);
		System.out.println("thumbnailName= "+thumbnailName);
		System.out.println("savedPath= "+savedPath);
		
		return "////" + yearPath + "////" + thumbnailName.substring(savedPath.length() + 2);
	}
	

	


	// 메일 답장하기 (답장대상 메일 읽어와야함)
	@RequestMapping(value = "/mail_reply", method = RequestMethod.GET)
	public String reply(Locale locale,@RequestParam("trans") String trans, 
			@RequestParam("mseq") int mseq,
			@RequestParam("eseq") int eseq,
			Model model,Principal principal) throws Exception {
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
		
		
		model.addAttribute("reply", ms.reply(eseq,trans, mseq));
		System.out.println( ms.reply(eseq,trans, mseq));
		model.addAttribute("addressbook",ms.selectAddressBook(eseq));

		return "mail/mail_reply";
	}
	

	
	// 메일 답장 쓰기(insert)
		@RequestMapping(value = "/mail_reply", method = RequestMethod.POST)
		public String replyPOST(MailDto dto, RedirectAttributes rttr) throws Exception {
			logger.info("");
			ms.mailwrite(dto);
			rttr.addFlashAttribute("msg", "send");
			rttr.addAttribute("eseq", dto.getEseq());

			return "redirect:/mail/mail_sendlist";
		}

//		//주소록
//		@RequestMapping(value = "/addressBook", method = RequestMethod.GET)
//		public String home(@RequestParam("eseq") int eseq, Model model) throws Exception {
//			logger.info("");
//			model.addAttribute("addressbook",ms.selectAddressBook(eseq));
//			
//			
//			return "mail/addressBook";
//		}
		
}

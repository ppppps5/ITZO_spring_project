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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
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

import com.itzo.dto.ApproveDto;
import com.itzo.dto.EmpImgDto;
import com.itzo.service.ApproveService;
import com.itzo.vo.PageMaker;

@Controller
@RequestMapping("/approve/*")
public class ApproveController {
	@Inject
	private ApproveService as;

	@Resource(name="uploadPath")//자원 주입?
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ApproveController.class);
	
	// 결재거절(기결함)
	@RequestMapping(value = "/approve_cancle", method = RequestMethod.GET)
	public String cancle(@RequestParam("eseq") int eseq,Model model, PageMaker pm,Locale locale,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);
				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		
		System.out.println(pm);
		model.addAttribute("cancle", as.ApproveCancle(eseq, pm));
		System.out.println(as.ApproveCancle(eseq, pm));
		pm.setTotalCount(as.CancleCount(eseq, pm));
		
		return "approve/approve_cancle";
	}
	
	//기결함 결재 삭제
	@RequestMapping(value = "/approve_delete", method = RequestMethod.POST)
	public String cancledelete(@RequestParam("aseq") int aseq, @RequestParam("eseq") int eseq, Model model,Locale locale,RedirectAttributes rttr,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		as.cancledelete(aseq, eseq);
		
		rttr.addFlashAttribute("msg", "delete");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/approve/approve_cancle";
		}
	
	// 결재요청(미결함)
	@RequestMapping(value = "/approve_decide", method = RequestMethod.GET)
	public String decide(@RequestParam("eseq") int eseq,Model model, PageMaker pm,Locale locale,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );

		System.out.println(pm);
		model.addAttribute("decide", as.ApproveDecide(eseq, pm));
		System.out.println(as.ApproveDecide(eseq, pm));
		pm.setTotalCount(as.DecideCount(eseq, pm));
		
		return "approve/approve_decide";
	}
	
	// 결재개인문서함
	@RequestMapping(value = "/approve_each", method = RequestMethod.GET)
	public String each(@RequestParam("eseq") int eseq,Model model, PageMaker pm,Locale locale,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );

		System.out.println(pm);
		model.addAttribute("each", as.ApproveEach(eseq, pm));
		System.out.println(as.ApproveEach(eseq, pm));
		pm.setTotalCount(as.CancleCount(eseq, pm));
		
		return "approve/approve_each";
	}
	
	// 결재 작성(기안문작성)
	@RequestMapping(value = "/approve_write", method = RequestMethod.GET)			
	public void write(@RequestParam("eseq") int eseq,Locale locale, Model model,Principal principal) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );

	}

	// 결재 작성2(기안문 작성2)
	@RequestMapping(value = "/approve_write", method = RequestMethod.POST)
	public String writePOST(Locale locale,@RequestParam("eseq")int eseq,
		@RequestParam("title") String title,@RequestParam("content") String content
		, @Param("approval") String approval, RedirectAttributes rttr,
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

		/*
		 * as.ApproveWrite(dto, eseq); rttr.addFlashAttribute("msg", "send"); return
		 * "redirect:/approve/approve_decide";
		 */
		
		System.out.println("데이터를 찍어본다 : "+title+content);
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
		File target = new File(savedPath, savedName);// 경로,이름 저장
		FileCopyUtils.copy(File.getBytes(), target);// 실제 파일에 바이너리 코드를 저장

		String formatName = savedName.substring(savedName.lastIndexOf(".") + 1).toUpperCase();// 확장자 추출
		System.out.println(formatName);
		String uploadedFileName = null;
		if (formatName.equals("JPG") || formatName.equals("PNG") || formatName.equals("GIF")) {
			uploadedFileName = makeThumbnail(savedPath, yearPath, savedName);// 이미지면 썸네일 만듦
		} else {
			uploadedFileName = "////" + yearPath + "////" + savedName;
		}
		System.out.println(uploadedFileName);
		as.ApproveWrite2(eseq, title, content, approval,uploadedFileName);
		// 실제 업로드한 파일이름 저장
		rttr.addFlashAttribute("msg", "send");

		rttr.addAttribute("eseq", eseq);
		return "redirect:/approve/approve_decide";
	}

	private static String makeThumbnail(String savedPath, String yearPath, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(savedPath, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumbnailName = savedPath + "////" + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		// 뭔지 모르면 찍어보기
		System.out.println("yearPath= " + yearPath);
		System.out.println("thumbnailName= " + thumbnailName);
		System.out.println("savedPath= " + savedPath);

		return "////" + yearPath + "////" + thumbnailName.substring(savedPath.length() + 2);
	}
	
	//미결함 결재 수정
	@RequestMapping(value = "/approve_update", method = RequestMethod.GET)
	public void update(@RequestParam("aseq") int aseq,@Param("eseq") int eseq,Model model,Locale locale,Principal principal) throws Exception {
		System.out.println(as.selectContent(aseq, eseq));
		model.addAttribute("decideupdate",as.selectContent(aseq, eseq));

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );

		}

	//미결함 결재 수정
	@RequestMapping(value = "/approve_update", method = RequestMethod.POST)
	public String dupdate(@RequestParam("title") String title, @RequestParam("content") String content,@RequestParam("aseq") int aseq, @RequestParam("eseq") int eseq, Model model,Locale locale,RedirectAttributes rttr,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
		as.decideupdate(title, content, aseq, eseq);
		
		rttr.addFlashAttribute("msg", "update");
		rttr.addAttribute("eseq",eseq);
		return "redirect:/approve/approve_decide";
		}

	// 팀별결재현황
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public String approve(@Param("eseq") int eseq, @Param("myapprove") String myapprove, Model model,Locale locale,Principal principal) throws Exception {
		logger.info("");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
		
		System.out.println(user);

				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
		
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );

		model.addAttribute("approve", as.Approve(eseq));
		model.addAttribute("approvem", as.Approvem(eseq));
		System.out.println(as.Approve(eseq));
		
		return "approve/approve";
	}
	
	
	//결재내용
		@RequestMapping(value = "/approve_content", method = RequestMethod.GET)
		public String content(@RequestParam("aseq") int aseq,@Param("eseq") int eseq,Model model,Locale locale,Principal principal) throws Exception {
			model.addAttribute("aseq",aseq);
			logger.info("");
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			String userId=principal.getName();	//로그인된 유저 아이디 받아오기
			int user=Integer.parseInt(userId);	//받아온 아이디는 문자열이라 숫자로 변환=eseq가 숫자라서
			
			System.out.println(user);

					
			//로그인 권한 가져오기. 어드민이면 트루.
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
			Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
			boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
			
			model.addAttribute("admin", admin);
			model.addAttribute("serverTime", formattedDate );
			model.addAttribute("userName",userId );

			model.addAttribute("acontent", as.selectContent(aseq, eseq));
			System.out.println(as.selectContent(aseq, eseq));

			return "approve/approve_content";
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
		
		
		
		@ResponseBody
		@RequestMapping(value ="/allFile", method = RequestMethod.POST)//해당 글에 대한 업로드 파일 가져오기
		public ResponseEntity<List<String>> allFile(@RequestParam("aseq") int aseq) throws Exception {
			System.out.println("업로드 파일 가져오기1");

			ResponseEntity<List<String>> entity=null;

			System.out.println(aseq);
			
		    try {
		    	entity = new ResponseEntity<>(as.getFiles(aseq), HttpStatus.OK);
		    	//JSON형태로 처리->RESTFULL사용
		    } catch (Exception e) {
		      e.printStackTrace();
		      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }

			return entity;
		}
		
		//결재 내용에서 업로드 된 파일 불러오기
		@ResponseBody
		@RequestMapping(value ="/allFile", method = RequestMethod.GET)//해당 글에 대한 업로드 파일 가져오기
		public ResponseEntity<List<String>> allFile2(@RequestParam("aseq") int aseq) throws Exception {
			System.out.println("업로드 파일 가져오기2");

			ResponseEntity<List<String>> entity=null;

			System.out.println(aseq);
			
		    try {
		    	entity = new ResponseEntity<>(as.getFiles(aseq), HttpStatus.OK);
		    	//JSON형태로 처리->RESTFULL사용
		    } catch (Exception e) {
		      e.printStackTrace();
		      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }

			return entity;
		}
	
	
	
	
		
}

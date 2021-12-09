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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itzo.dto.BoardDto;
import com.itzo.service.BoardService;
import com.itzo.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	private BoardService bs;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//게시판 목록
	@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	public String listSearchCriteria(Locale locale, Model model, @RequestParam("eseq") int eseq, PageMaker pm, Principal principal) throws Exception {
		logger.info("");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formmattedDate = dateFormat.format(date);
		String userId = principal.getName(); //로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId); //받아온 아이디 형변환
		
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
				
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formmattedDate );
		model.addAttribute("userName",userId );
				
		System.out.println(user);
		System.out.println(pm);
		model.addAttribute("listSearchCriteria", bs.listSearchCriteria(eseq, pm));
		System.out.println("check");
		System.out.println(bs.listSearchCriteria(eseq, pm));
		pm.setTotalCount(bs.listSearchCount(eseq, pm));
		
		return "board/board_list";
	}
	
	//게시판 내용 읽어오기
	@RequestMapping(value = "/board_content", method = RequestMethod.GET)
	public String content(Locale locale, @RequestParam("bseq") int bseq, @RequestParam("eseq") int eseq, Model model,Principal principal) throws Exception {
		model.addAttribute("bseq",bseq);
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
		
		model.addAttribute("content", bs.content(eseq, bseq));
		System.out.println(bs.content(eseq, bseq));
		
		return "board/board_content";
	}
	
	//게시판 업로드 파일 가져오기
	@ResponseBody
	@RequestMapping(value ="/allFile", method = RequestMethod.POST)
	public ResponseEntity<List<String>> allFile(@RequestParam("bseq") int bseq) throws Exception {
		System.out.println("업로드 파일 가져오기1");

		ResponseEntity<List<String>> entity=null;

		System.out.println(bseq);
		
	    try {
	    	entity = new ResponseEntity<>(bs.getFiles(bseq), HttpStatus.OK);
	    	//JSON형태로 처리->RESTFULL사용
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

		return entity;
	}
	
	//게시판 업로드 파일 불러오기
	@ResponseBody
	@RequestMapping(value ="/allFile", method = RequestMethod.GET)
	public ResponseEntity<List<String>> allFile2(@RequestParam("bseq") int bseq) throws Exception {
		System.out.println("업로드 파일 가져오기2");

		ResponseEntity<List<String>> entity=null;

		System.out.println(bseq);
		
	    try {
	    	entity = new ResponseEntity<>(bs.getFiles(bseq), HttpStatus.OK);
	    	//JSON형태로 처리->RESTFULL사용
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

		return entity;
		
	}
	
	//업로드 파일 처리
	@ResponseBody
	@RequestMapping("/displayFile")
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
	
	//게시판 삭제
	@RequestMapping(value = "/board_delete", method = RequestMethod.POST)
	public String delete(Locale locale, @RequestParam("bseq") int bseq, @RequestParam("eseq") int eseq, Model model, RedirectAttributes rttr, Principal principal) throws Exception {
		logger.info("");
		System.out.println("dkanrjsk");
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

		
		bs.delete(bseq,eseq);
		System.out.println(bseq);
		System.out.println(eseq);
		rttr.addFlashAttribute("msg", "deletesuccess");
		rttr.addAttribute("eseq",eseq);

		return "redirect:/board/board_list";
	}
	
	//게시판 게시글 작성
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public void write(Locale locale, @RequestParam("eseq") int eseq, Model model, Principal principal) throws Exception {
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
				
	}
	
	//게시판 게시글 작성
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public String writePost(Locale locale, @RequestParam("eseq") int eseq, @RequestParam("bWriter") String bWriter,
			@RequestParam("bTitle") String bTitle,@RequestParam("bContent") String bContent
			, RedirectAttributes rttr,
			@RequestParam("bFile") MultipartFile File, Model model, Principal principal) throws Exception {
		logger.info("");
		
		//로그인(사원번호 가져오기)
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
				
		String formattedDate = dateFormat.format(date);
		String userId=principal.getName();	//로그인된 유저 아이디 받아오기
		int user=Integer.parseInt(userId);	//받아온 아이디 형변환
				
		//로그인 권한 가져오기. 어드민이면 트루.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities(); 
		boolean admin=authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
				
		model.addAttribute("admin", admin);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("userName",userId );
				
		System.out.println(user);
		//로그인(사원번호 가져오기)
				
		System.out.println("데이터를 찍어본다 : "+bWriter+bTitle+bContent);
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
		bs.write2(eseq, bWriter, bTitle, bContent,uploadedFileName);
		//실제 업로드한 파일이름 저장
		rttr.addFlashAttribute("msg", "complete");
		rttr.addAttribute("eseq", eseq);
		
		return "redirect:/board/board_list";
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
	
	//게시판 수정
	@RequestMapping(value = "/board_update", method = RequestMethod.GET)
	public String updateGet(Locale locale,
			@RequestParam("bseq") int bseq, 
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
		
		model.addAttribute("content", bs.content(eseq, bseq));
		System.out.println(bs.content(eseq, bseq));
		
		return "board/board_update";
	}

	//게시판 수정
	@RequestMapping(value = "/board_update"	, method = RequestMethod.POST)
	public String updatePost(@RequestParam("bTitle") String bTitle, 
						   @RequestParam("bContent") String bContent, 
						   @RequestParam("bseq") int bseq, 
						   @RequestParam("eseq") int eseq, 
						   Locale locale, BoardDto dto, RedirectAttributes rttr) throws Exception {
		logger.info("");
		bs.update(bTitle, bContent, bseq, eseq);
		rttr.addFlashAttribute("msg", "update");
//		rttr.addAttribute("update", bs.update(bTitle, bContent, bseq, eseq));
		rttr.addAttribute("update", dto.getbTitle());
		rttr.addAttribute("update", dto.getbContent());
		rttr.addAttribute("update", dto.getBseq());
		rttr.addAttribute("update", dto.getEseq());
//		System.out.println(bs.update(bTitle, bContent, bseq, eseq));
		rttr.addAttribute("eseq", eseq);

		return "redirect:/board/board_list";
	}
	
}
package com.itzo.ex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itzo.dto.NoticeDto;
import com.itzo.service.NoticeService;
import com.itzo.vo.PageMaker;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Inject
	private NoticeService ns;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public void list(PageMaker pm, Model model) throws Exception {
		logger.info("listPage");
		System.out.println(pm);
		model.addAttribute("list", ns.listSearchCriteria(pm));
		pm.setTotalCount(ns.listSearchCount(pm));

	}
	
	@RequestMapping(value = "/notice_write", method = RequestMethod.GET)
	public void writeGet() throws Exception {

	}
	
	@RequestMapping(value = "/notice_write", method = RequestMethod.POST)
	public String writePost(@RequestParam("nWriter") String nWriter,
			@RequestParam("nTitle") String nTitle,@RequestParam("nContent") String nContent
			, RedirectAttributes rttr,
			@RequestParam("nFile") MultipartFile nFile, Model model) throws Exception {
		logger.info("");
		
		System.out.println("데이터를 찍어본다 : "+nWriter+nTitle+nContent);
		System.out.println("originalName: " + nFile.getOriginalFilename());

		String savedName = System.currentTimeMillis() + "_" + nFile.getOriginalFilename();
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
		FileCopyUtils.copy(nFile.getBytes(), target);//실제 파일에 바이너리 코드를 저장
	

		String formatName = savedName.substring(savedName.lastIndexOf(".") + 1).toUpperCase();//확장자 추출
		System.out.println(formatName);
		String uploadedFileName = null;
		if (formatName.equals("JPG") || formatName.equals("PNG") || formatName.equals("GIF")) {
			uploadedFileName = makeThumbnail(savedPath, yearPath, savedName);//이미지면 썸네일 만듦
		} else {
			uploadedFileName = "////" + yearPath + "////" + savedName;
		}
		System.out.println(uploadedFileName);
		ns.write2(nWriter,nTitle,nContent,uploadedFileName);
		//실제 업로드한 파일이름 저장
		rttr.addFlashAttribute("msg", "send");
		
		return "redirect:/notice/notice_list";
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
	
	@RequestMapping(value = "/notice_content", method = RequestMethod.GET)
	public void read(@RequestParam("nseq") int nseq, PageMaker pm, Model model) throws Exception {
		System.out.println(pm);
		model.addAttribute(ns.read(nseq));
	}

	@RequestMapping(value = "/notice_update", method = RequestMethod.GET)
	public void updateGet(@RequestParam("nseq") int nseq, PageMaker pm, Model model) throws Exception {
		System.out.println(pm);
		model.addAttribute(ns.read(nseq));

	}

	@RequestMapping(value = "/notice_update", method = RequestMethod.POST)
	public String updatePost(NoticeDto notice, PageMaker pm, Model model, RedirectAttributes rttr) throws Exception {
		ns.update(notice); 
		System.out.println(pm);
		
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/notice/notice_list";
	}
	
	@RequestMapping(value = "/notice_delete", method = RequestMethod.POST)
	public String delete(@RequestParam("nseq") int nseq, PageMaker pm, Model model, RedirectAttributes rttr)
			throws Exception {
		ns.delete(nseq);
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/notice/notice_list";
	}
	
	
	
}
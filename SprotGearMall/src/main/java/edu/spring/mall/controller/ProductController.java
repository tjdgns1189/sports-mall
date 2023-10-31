package edu.spring.mall.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.service.ProductService;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	 @Resource(name = "uploadPath")
	 private String uploadPath;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private LikesDAO likesDAO;

	@GetMapping("/list")

	public void list(Model model, Integer page, Integer numsPerPage) {

		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ProductVO> list = productService.read(criteria);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	} // end list()

	@GetMapping("/productListTest")
	public void listTestGET(Model model, Integer page, Integer numsPerPage) {
		logger.info("listTest() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ProductVO> list = productService.read(criteria);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
		
		

	} // end list()
	


	@GetMapping("/payment")
	public void paymentGET(Model model, Integer productId) {

		logger.info("paymentGET() 호출");
		ProductVO vo = dao.selectById(productId);
		model.addAttribute("vo", vo);
	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()

	@PostMapping("/register")

	public String registerPOST(ProductVO vo, RedirectAttributes reAttr,MultipartFile file) {
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		logger.info("파일명 " + file.getOriginalFilename());
		logger.info("파일 사이즈 " + file.getSize());
		
		if (!file.isEmpty()) {
			try {
				String savedFileName = saveUploadFile(file);
				vo.setProductImgPath(savedFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int result = productService.create(vo);

		logger.info(result + "result");
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/product/list";
		} else {
			return "redirect:/product/register";
		}
	} // end registerPOST()
	
	private String saveUploadFile(MultipartFile file) {
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		
		try {
			FileCopyUtils.copy(file.getBytes(),target);
			return savedName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/detail")
	public void detail(int productId, Principal principal, Model model) {
		boolean isLiked = false;

		logger.info("detail() 호출  = " + productId);
		ProductVO vo = productService.read(productId);
		model.addAttribute("vo", vo);
	
		if (principal != null) {
			logger.info("principal호출" + principal.getName());

			String memberId = principal.getName();
			LikesVO likesVO = new LikesVO(0, memberId, productId);
			int result = likesDAO.select(likesVO);
			if(result == 1) {
				isLiked = true;
				model.addAttribute("isLiked", isLiked);
				return;
			}
		}
		model.addAttribute("isLiked", isLiked);

		

	} // end detail()

	@GetMapping("/update")

	public void updateGET(Model model, int productId, Integer page) {

		logger.info("updateGET() 호출 : productName = " + productId);
		ProductVO vo = productService.read(productId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);

	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(ProductVO vo, Integer page) {

		logger.info("updatePOST() 호출: vo = " + vo.toString());
		int result = productService.update(vo);

		if (result == 1) {
			return "redirect:/board/list?page=" + page;
		} else {
			return "redirect:/board/update?productId=" + vo.getProductId();
		}
	} // end updatePOST()

	@PostMapping("/delete")
	public String delete(String productName) {
		logger.info("delete()호출 : productName = " + productName);
		int result = productService.delete(productName);
		if (result == 1) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/list";
		}
	} // end delete()

} // end ProductController


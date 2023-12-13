package edu.spring.mall.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductQnaJoinReplyVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.persistence.CartDAO;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.OrdersDAO;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.service.CartService;
import edu.spring.mall.service.LikesService;
import edu.spring.mall.service.OrderService;
import edu.spring.mall.service.ProductQnaService;
import edu.spring.mall.service.ProductService;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	String productImgPathName = null;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductQnaService qnaService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private LikesService likesService;

	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
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
	public void paymentGET(Model model, Integer productId, Principal principal) throws Exception {
		logger.info("paymentGET() 호출");

		// productId있을 경우
		if (productId != null) {
			logger.info("paymentGET() 호출");
			ProductVO vo = productService.read(productId);
			model.addAttribute("vo", vo);
		}
		// productId 없을경우
		else {
			String memberId = principal.getName();
			logger.info("paymnet에서 memberId는 : " + memberId);
			List<CartProductJoinVO> list = cartService.read(memberId);
			model.addAttribute("list", list);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonList = objectMapper.writeValueAsString(list);
			model.addAttribute("jsonList", jsonList);
		}
	}

	@GetMapping("/register")
	public void registerGET(Model model) {
		logger.info("registerGET()");

	} // end registerGET()

	@PostMapping("/register")
	public String registerPOST(@RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice, @RequestParam("productStock") int productStock,
			@RequestParam("productMaker") String productMaker, @RequestParam("productImgPath") MultipartFile file,
			@RequestParam("productCategory") String productCategory,
			@RequestParam("productContent") String productContent) throws IOException {

		logger.info("registerPOST 호출");

		String productImgPath = file.getOriginalFilename();

		ProductVO vo = new ProductVO(productName, productPrice, productStock,

				productMaker, productImgPath, productCategory, productContent);
		int result = productService.create(vo, file);

		if (result == 1) {
			logger.info("상품등록 성공");
			return "redirect:/";
		}
		return "redirect:/admin/adminPage";

	}

	@GetMapping("/detail")
	public void detail(int productId, Principal principal, Model model) {
		boolean isLiked = false;
		logger.info("detail() 호출  = " + productId);
		Map<String, Object> map = productService.readProductById(productId);
		List<ReviewVO> review = (List<ReviewVO>) map.get("review");
		ProductVO product = (ProductVO) map.get("product");
		model.addAttribute("review", review);
		model.addAttribute("product", product);

		// 리뷰 별점 평균용
		int sum = 0;
		int count = review.size();
		double avg = 0;
		if (review != null && !review.isEmpty()) {
			for (ReviewVO x : review) {
				sum += x.getReviewRating();
			}
			avg = (double) sum / count;
			avg = Math.floor(avg * 10) / 10;

		}
		model.addAttribute("avg", avg);
		model.addAttribute("reviewCount", count);

		// 좋아요 확인용임

		if (principal != null) {
			logger.info("principal호출" + principal.getName());
			String memberId = principal.getName();
			LikesVO likesVO = new LikesVO(0, memberId, productId);
			int result = likesService.count(likesVO);
			if (result == 1) {
				isLiked = true;
			}

		}
		model.addAttribute("isLiked", isLiked);

		// 제품문의
		PageCriteria criteria = new PageCriteria();
		List<ProductQnaJoinReplyVO> qnaList = qnaService.read(productId, criteria);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qnaService.getTotalCounts(productId));
		pageMaker.setPageData();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String memberId = auth.getName();

		model.addAttribute("qnaList", qnaList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("principal", memberId);

	} // end detail()

	@GetMapping("/update")
	public void updateGET(Model model, int productId, Integer page) {

		logger.info("updateGET() 호출 : productName = " + productId);

		ProductVO vo = productService.read(productId);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String memberId = auth.getName();

		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("principal", memberId);

	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(@RequestParam("productId") int productId, @RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice, @RequestParam("productStock") int productStock,
			@RequestParam("productMaker") String productMaker, @RequestParam("productImgPath") MultipartFile file,
			@RequestParam("productCategory") String productCategory,
			@RequestParam("productContent") String productContent) throws IOException {

		logger.info("updatePOST() 호출: vo");
		String productImgPath = file.getOriginalFilename();

		ProductVO vo = new ProductVO(productId, productName, productPrice, productStock,

				productMaker, productImgPath, productCategory, productContent);
		int result = productService.update(vo, file);

		if (result == 1) {
			logger.info("상품 수정 완료");
		} else {
			logger.info("상품 수정 실패");
		}
		return "redirect:/product/detail?productId=" + vo.getProductId();

	} // end updatePOST()

	@PostMapping("/delete")
	public String delete(int productId) {
		logger.info("delete()호출 : productName = " + productId);

		int result = productService.delete(productId);
		if (result == 1) {
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	} // end delete()

	@GetMapping("/cart")
	public String cartGET() {
		return "product/cart";
	}

	@GetMapping("/search")
	public void search(@RequestParam(name = "searchtext") String searchText, Model model, Integer page,
			Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		List<ProductVO> list = productService.readBySearchText(searchText, criteria);

		// 매개변수를 criteria를 가지고 있는 list

		// 내가 받아야하는건 reviewProductList
		model.addAttribute("list", list);
		model.addAttribute("searchText", searchText);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

		/*
		 * ObjectMapper objm = new ObjectMapper(); String productList =
		 * objm.writeValueAsString(list);
		 */
	}

	@PostMapping(value = "/orderlists", produces = "application/json")
	public ResponseEntity<Integer> orderlistsPost(@RequestBody List<OrdersVO> ordersList) throws Exception {
		logger.info("ordersList = " + ordersList.toString());
		int result = 0;
		for (OrdersVO ordersVO : ordersList) {
			result += orderService.create(ordersVO);
		}

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/recent")
	public void recentGET() {

	}

	@PostMapping(value = "/result")
	public void resultPOST(Model model, OrdersVO vo, Principal principal) throws Exception {
		logger.info("resultPOST() 호출 : vo = " + vo.toString());
		int result = orderService.create(vo);

		int productId = vo.getProductId();
		int orderId = vo.getOrderId();
		ProductVO productVO = productService.read(productId);
		OrdersVO ordersVO = orderService.read(orderId);
		model.addAttribute("productVO", productVO);
		model.addAttribute("vo", vo);
		model.addAttribute("productPrice", vo.getProductPrice());
	}

	@GetMapping(value = "/result", produces = "application/json")
	public void resultGet(Model model, OrdersVO vo, Principal principal) {
		logger.info("resultPOST() 호출 : vo = " + vo.toString());

		try {
			int result = orderService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int productId = vo.getProductId();
		int orderId = vo.getOrderId();
		ProductVO productVO = productService.read(productId);
		model.addAttribute("productVO", productVO);
		model.addAttribute("vo", vo);
		model.addAttribute("productPrice", vo.getProductPrice());
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductVO> searchJsonData(@RequestParam(name = "searchtext") String searchText,
			@RequestParam(name = "order", required = false) String order, Model model, Integer page,
			Integer numsPerPage) {

		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		PageCriteria criteria = new PageCriteria();

		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<ProductVO> list = new ArrayList<>();

		if ("asc".equals(order) || "desc".equals(order) || "registration".equals(order)
				|| "alphabetize".equals(order)) {
			list = productService.readBySearchText(searchText, criteria);
		} else if ("likes".equals(order)) {
			list = productService.searchLikesCount(searchText, criteria);
		} else if ("rating".equals(order)) {
			list = productService.searchRating(searchText, criteria);
		} else if ("review".equals(order)) {
			list = productService.searchReviewCount(searchText, criteria);
		}

		return list;

	}
} // end ProductController

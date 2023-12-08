package edu.spring.mall;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDAO dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,Integer page, Integer numsPerPage) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    model.addAttribute("authorities", authorities);
				
		PageCriteria criteria = new PageCriteria();
		criteria.setNumsPerPage(100);
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
	//	List<ProductVO> list = productService.read(criteria);
	//	model.addAttribute("list",list);
		
		List<ProductVO> soccerBallList = productService.readSoccerBall();
		model.addAttribute("soccerBallList", soccerBallList);
		
		if(soccerBallList.size() == 0) {
			logger.info("soccerBallList안에 아무것도 안들었음");
		}
		for(ProductVO x : soccerBallList) {
			logger.info("축구공 : " + x.getProductName());
		}

		List<ProductVO> baseBallList = productService.readBaseBall();
		model.addAttribute("baseBallList", baseBallList);

		List<ProductVO> basketBallList = productService.readBasketBall();
		model.addAttribute("basketBallList", basketBallList);
		
		
		PageMaker pageMaker = new PageMaker();
		PageCriteria pageCriteria = new PageCriteria();
		
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker",pageMaker);
	    
		return "home";
	}
	
	@GetMapping("/index")
	public void indexGET(Model model) {
		logger.info("index호출");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    model.addAttribute("authorities", authorities);
	}
	
	@GetMapping("/product/pay")
	public void payGET() {
		
		
	}
	
}

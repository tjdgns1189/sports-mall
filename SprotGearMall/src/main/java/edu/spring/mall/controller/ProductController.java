package edu.spring.mall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.mall.domain.AttachImageVO;
import edu.spring.mall.domain.LikesVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.persistence.LikesDAO;
import edu.spring.mall.persistence.ProductDAO;
import edu.spring.mall.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	 @Resource(name = "uploadFilePath")
	 private String uploadFilePath;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private LikesDAO likesDAO;

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
		//매개변수를 criteria를 가지고 있는 list
		
		//내가 받아야하는건 reviewProductList
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
	public void registerGET(Model model) {
		logger.info("registerGET()");
		
	} // end registerGET()


	
	@PostMapping(value="/register" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> testregisterPOST(MultipartFile[] productImgPath) {
		   // url 매핑 매서드의 리턴 타입을 void에서 ResponseEntity<List<AttachImageVO>>으로 변경. 
		   // 의미는 반환 타입이 ResponseEntity객체이고 Http의 body에 추가될 데이터는 List<AttachImageVO>라는 의미
		
		logger.info("testregister()호출");
		
		String uploadFolder = "C:\\upload";
		
		// 날짜 폴더 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();

		}
		
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		for(MultipartFile multipartFile : productImgPath) {
			
			// 이미지 정보 객체
			AttachImageVO vo = new AttachImageVO();
			
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid 적용 파일 이름 */

			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */

			try {
				multipartFile.transferTo(saveFile);
				
//				/* 썸네일 생성(ImageIO) */
//				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
//				
//				// bo_image = buffered original image
//				BufferedImage bo_image = ImageIO.read(saveFile);
//				
//				/* 비율 */
//				double ratio = 3;
//				/*넓이 높이*/
//				int width = (int) (bo_image.getWidth() / ratio);
//				int height = (int) (bo_image.getHeight() / ratio);	
//				
//				// bt_image = buffered thumbnail image
//				//  BufferedImage 생성자의 매개변수는 '넓이', '높이', '생성될 이미지의 타입'
//				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//								
//				Graphics2D graphic = bt_image.createGraphics();
//				
//				// draowImage 메서드의 첫 번째 인자는 그려놓고자 하는 이미지
//				// 두 번째, 세 번째 인자는 그림을 어느 좌표부터 그릴 것인지에 대한 'x'값과 'y'값 (좌 상단 맨 끝점이 "0, 0")
//				// 네 번째, 다섯 번째 인자 값은 첫 번째 인자로 작성한 이미지의 '넓이'와 '높이'이고 
				// 지정한 '넓이'와 '높이'로 이미지 크기가 확대or축소되며 크기가 변경된 이미지가 그려짐
//				// 여섯 번째 인자는 ImageObserver 객체. ImageObserver는 이미지의 정보를 전달받아서 이미지를 업데이트시키는 역할. 일반적인 경우 null을 전달
//				graphic.drawImage(bo_image, 0, 0,width,height, null);
//					
//				// write() 메서드의 첫 번째 인자는 파일로 저장할 이미지
//				// 우리가 만든 썸네일 이미지(bt_image)를 인자로 부여
//				// 두 번째 인자는 어떠한 이미지 형식으로 저장할 것인지 String 타입으로 작성
//				// 'jpg', 'png' 등 자신이 원하는 형식을 지정
//				// 세 번째 인자는 우리가 앞서 썸네일 이미지가 저장될 경로와 이름으로 생성한 File 객체(thumbnailFile)를 부여
//				ImageIO.write(bt_image, "jpg", thumbnailFile);
				
				
				// 위에 싹다 주석처리된 코드는 ImageIO를 사용하여 썸네일을 생성하는 코드이고
				// 아래는Thumbnails 라이브러리를 사용하여 썸네일을 생성하는 코드. 이게 더 간편
				/* 방법 2 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
				BufferedImage bo_image = ImageIO.read(saveFile);

					//비율 
					double ratio = 3;
					//넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);					
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
				

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list.add(vo);
		}
		
		// ResponseEntity 참조 변수를 선언하고 생성자로 초기화
		// 이 생성자를 통해서 Http의 바디에 추가될 데이터는 List <AttachImageVO>이고 상태 코드가 OK(200)인 ReseponseEntity 객체가 생성. 
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		return result;
		
		//해당 메서드의 전체적인 과정을 요약하면 다음과 같다.
		
		//1. 이미지 파일 저장
		//2. 썸네일 이미지 파일 생성 및 저장
		//3. 각 이미지 정보 List 객체에 저장
		//4. ResponseEntity를 통해서 뷰(view)로 http 상태 코드가 200이고 http Body에 이미지 정보가 담긴 List 객체를 전송
		// 	 추가적으로 설명하면 뷰(view)에서 ajax를 통해 요청할 때 JSON타입의 데이터를 요청을 하였습니다. 
		// 	 따라서 뷰로 List객체를 반환하는 과정에서 List객체를 JSON 형식의 문자열로 변환을 해주어야 합니다. 
		//   이때 클래스 패스(classpath)에 Jackson이 존재해야만 JSON형식으로 스프링이 변환을 시켜줍니다.(이미 존재함) 
	
		
	} // end registerPOST()
	
	

//	@PostMapping("/register")
//	public String registerPOST(@ModelAttribute ProductVO vo, RedirectAttributes reAttr, MultipartFile uploadImgFile) {
//		logger.info("registerPOST() 호출");
//		logger.info(vo.toString());
//		logger.info("파일명 " + uploadImgFile.getOriginalFilename());
//		logger.info("파일 타입 : " + uploadImgFile.getContentType());
//		logger.info("파일 사이즈 " + uploadImgFile.getSize());

//		
//		if (!uploadImgFile.isEmpty()) {
//			try {
//				String savedFileName = saveUploadFile(uploadImgFile);
//				vo.setProductImgPath(savedFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		int result = productService.create(vo);
//
//		logger.info(result + "result");
//		if (result == 1) {
//			reAttr.addFlashAttribute("insert_result", "success");
//			return "redirect:/product/list";
//		} else {
//			return "redirect:/product/register";
//		}
//	} // end registerPOST()
//	
//	private String saveUploadFile(MultipartFile uploadImgFile) {
//		String uploadPath = new String("C:\\upload\\temp");
//		UUID uuid = UUID.randomUUID();
//		String savedName = uuid + "_" + uploadImgFile.getOriginalFilename();
//		File target = new File(uploadPath, savedName);
//		
//		try {
//			FileCopyUtils.copy(uploadImgFile.getBytes(),target);
//			return savedName;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	} // end saveUploadFile()
	



	

	@GetMapping("/detail")
	public void detail(int productId, Principal principal, Model model) {
		boolean isLiked = false;
		logger.info("detail() 호출  = " + productId);
		Map<String,Object> map = productService.readProductById(productId);
		List<ReviewVO> review = (List<ReviewVO>) map.get("review");
		ProductVO product = (ProductVO) map.get("product");
		model.addAttribute("review", review);
		model.addAttribute("product", product);
		
		//리뷰 별점 평균용
		int sum = 0;
		int count = review.size();
		double avg = 0;
		if(review!= null  && !review.isEmpty()) {
			for(ReviewVO x : review) {
				sum+=x.getReviewRating();
			}
			avg = (double) sum / count;
		}
		model.addAttribute("avg", avg);
		model.addAttribute("reviewCount", count);
		//좋아요 확인용임
		if (principal != null) {
			logger.info("principal호출" + principal.getName());
			String memberId = principal.getName();
			LikesVO likesVO = new LikesVO(0, memberId, productId);
			int result = likesDAO.select(likesVO);
			if(result == 1) {
				isLiked = true;
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
	
	@GetMapping("/cart")
	public String cartGET() {

	    return "product/cart";
	}

} // end ProductController

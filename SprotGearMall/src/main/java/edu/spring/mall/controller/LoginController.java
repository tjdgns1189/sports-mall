package edu.spring.mall.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.persistence.MemberDAO;

@RestController
@RequestMapping(value = "/member")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MemberDAO dao;


	@PostMapping("/checkid")
    public ResponseEntity<Integer> checkId(@RequestBody Map<String, String> request) {
		logger.info("아이디 중복체크 메소드 호출");
		Integer result = dao.select(request.get("memberId"));
		logger.info("중복 아이디 갯수 : " + result);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
	private final AuthenticationManager authenticationManager;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
		Authentication authenticationRequest =
			UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
		Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);
		// ...
	}

	public record LoginRequest(String username, String password) {
	}
	
	

}

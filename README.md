# 스포츠용품 쇼핑몰
이 프로젝트는 스포츠용품 쇼핑몰 웹사이트로, 다양한 쇼핑몰 사이트에서 얻은 경험을 기반으로 스프링 프레임워크를 사용하여 구축되었습니다.

## 개발 기간
2023년 00월 00일 - 2023년 12월 15일

## 팀 구성
- **김빛찬**: Maven 프로젝트 설정, 로그인 기능, 마이페이지, UI 디자인, 상품 기능, 관리자 및 사용자 알림 시스템, 공지사항 게시판, 1:1 채팅 문의 기능, 에러 페이지 구현
- **김성훈**: 상품구매, 구매내역, 최근 본 상품, 장바구니, 자유게시판, 대댓글

## 개발 환경
- **Java 버전**: 11
- **IDE**: Spring Tool Suite 3
- **Framework**: Spring MVC, Spring Security, Google Cloud Buckets, OAuth2

## 주요 기능
- **사용자 인증 및 보안**: Spring Security와 OAuth2를 활용한 로그인 및 사용자 인증 시스템 구축
- **마이페이지 및 사용자 관리**: 사용자 정보 관리를 위한 마이페이지 기능 구현
- **상품 관리**: 효율적인 상품 검색 및 구매 시스템 개발
- **UI/UX 디자인**: 사용자 경험 향상을 위한 친화적 인터페이스 디자인
- **알림 시스템**: 중요 정보를 실시간으로 알리는 알림 기능
- **게시판 및 1:1 채팅**: 의견 수렴 및 문의 처리를 위한 게시판 및 채팅 기능
- **에러 처리**: 원활한 사용자 경험을 위한 에러 페이지 구현
- **상품 구매**: 카카오페이API를 이용한 구매기능
- **구매 내역**: 구매한 상품내역확인, 삭제기능
- **최근 본 상품**: 비로그인상태에서 열람했던 상품들 목록확인
- **장바구니**: 구매원하는 상품들 갯수에 따른 총가격 한번에 확인
- **자유게시판**: 글, 댓글, 대댓글 각각 입력, 수정, 삭제 기능

## 데이터베이스
데이터베이스 구성 및 관리는 프로젝트의 핵심 요소입니다. 아래는 사용된 주요 테이블과 그 세부 사항입니다.
데이터베이스 연결을 하지않으면 이 프로젝트는 실행되지 않습니다

### MEMBER
- 보유컬럼 MEMBER_ID, PASSWORD, NAME, PHONE, EMAIL, USER_GRADE IS_OAUTH_LOGIN, ZONECODE, ADDRESS, DETAIL_ADDRESS
- USER_GRADE는 시큐리티 기본 설정인 ROLE_USER, ROLE_ADMIN으로 표기
- 주소 구성은 우편 번호, 주소, 상세주소 구성 EX) 05551 서울 송파구 올림픽로 300 1층 어디일때
  ZONECODE = 05551, ADDRESS = 서울 송파구 올림픽로 300, DETAIL_ADDRESS = 1층 어디
- IS_OAUTH_LOGIN은 사용자가 소셜로그인(네이버, 구글)으로 가입한 회원일경우 해당 컬럼의 데이터를 'Y' 또는 'N'으로 표시


### PRODUCT
- 보유컬럼 PRODUT_ID, PRODUCT_NAME, PRODUCT_STOCK, PRODUCT_MAKER, PRODUCT_IMG_PATH, PRODUCT_CATEGORY, PRODUCT_IS_DELETED,
  PRODUCT_CONTENT, PRODUCT_CREATED_DATE 로 구성
- 설명2

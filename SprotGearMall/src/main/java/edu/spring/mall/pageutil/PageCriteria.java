package edu.spring.mall.pageutil;

public class PageCriteria {
	private int page;  //현재 페이지 번호
	private int numsPerPage; // 한페이지의 게시글 수
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 5;
	}
	
	public PageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	} // 현재 페이지에서 보여질 항목의 시작 인덱스를 계산
	  // (this.page - 1) * this.numsPerPage는 이전 페이지들에서 보여진 항목의 총 개수. 
	  // 그리고 + 1을 한 이유는 현재 페이지에서 첫 번째로 보여질 항목의 인덱스를 구하기 위함
	  // 예를 들어, 현재 페이지(this.page)가 1이고, 한 페이지당 보여질 항목의 수(this.numsPerPage)가 10이라면, 
	  // (1 - 1) * 10 + 1은 1이 됨. 
	  // 만약 현재 페이지가 2이면 (2 - 1) * 10 + 1은 11이 되어, 두 번째 페이지에서 첫 번째로 보여질 항목의 인덱스가 됨.
	
	
	public int getEnd() {
		return this.page * this.numsPerPage;
	} // this.page * this.numsPerPage는 현재 페이지까지의 항목 중 마지막 항목의 인덱스
	  // 예를 들어, 현재 페이지(this.page)가 1이고, 한 페이지당 보여질 항목의 수(this.numsPerPage)가 10이라면, 1 * 10은 10이 됨.
	  // 따라서 이 페이지에서 보여지는 항목의 끝 인덱스는 10이 됩니다. 만약 현재 페이지가 2이면 2 * 10은 20이 되어, 이 페이지에서 보여지는 항목의 끝 인덱스는 20이 됨.
	
	
}
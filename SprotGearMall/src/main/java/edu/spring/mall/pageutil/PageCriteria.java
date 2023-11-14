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
	}
	
	
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
	
	
}
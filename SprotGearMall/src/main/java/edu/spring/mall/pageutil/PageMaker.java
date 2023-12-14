 package edu.spring.mall.pageutil;

public class PageMaker {
	@Override
	public String toString() {
		return "PageMaker [criteria=" + criteria + ", 전체 게시글=" + totalCount + ", numsOfPageLinks=" + numsOfPageLinks
				+ ", startPageNo=" + startPageNo + ", endPageNo=" + endPageNo + ", hasPrev=" + hasPrev + ", hasNext="
				+ hasNext + "]";
	}

	private PageCriteria criteria;
	private int totalCount; //전체 게시글 갯수
	private int numsOfPageLinks;  // 페이지 번호 링크의 개수	
	private int startPageNo; //시작 페이지 링크 번호. 위 예시에서는 1과 4
	private int endPageNo; //끝 페이지 링크 번호. 위 예시에서는 3과 6
	private boolean hasPrev;  //화면에 보이는 시작 페이지 번호보다 작은숫자의 페이지 확인
	private boolean hasNext; //화면에 보이는 끝페이지 번호보다 큰 숫자 페이지가 있는지
	
	public PageMaker() {
		this.numsOfPageLinks = 3;
	}
	
	public PageCriteria getCriteria() {
		return criteria;
	}
	
	public void setCriteria(PageCriteria criteria) {
		this.criteria = criteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getNumsOfPageLinks() {
		return numsOfPageLinks;
	}
	
	public int getStartPageNo() {
		return startPageNo;
	}
	
	public int getEndPageNo() {
		return endPageNo;
	}
	
	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 
	public void setPageData() { 
		// 이 메소드는 페이지에 관한 데이터를 세팅함
		// ceil() 함수는 올림함수.
		int totalLinkNo = (int) Math.ceil((double) totalCount / criteria.getNumsPerPage());
		// numsOfPageLinks는 한 번에 표시되는 페이지 링크의 개수이고, totalLinkNo는 전체 페이지 링크의 총 개수.
		// 예를 들어 이전 / 1 / 2 / 3 / 다음 이면 numsOfPageLinks=3 인것이고 이것이 1~20개의 페이지링크가 있으면 totalLinkNo=20인 것
		// criteria.getNumsPerPage() 이 값과 totalLinkNo 이 값을 곱하면 totalCount 이 값이 되므로 역연산하면 totalCount를 구할 수 있지.
		int temp = (int) Math.ceil((double) criteria.getPage() / numsOfPageLinks) * numsOfPageLinks;
		// 이 코드는 페이지 링크의 범위를 결정하는 부분. 
		// 간단히 말하면, 현재 페이지 주변에 표시될 페이지 링크의 범위를 계산하고, 이를 기반으로 endPageNo를 설정합니다.
		// Math.ceil((double) criteria.getPage() / numsOfPageLinks) : 현재 페이지를 페이지 링크의 개수로 나눈 후에
		// 계산된 값을 올림하여 소수점 이하를 제거합니다. 이렇게 하면 현재 페이지가 몇 번째 그룹에 속하는지가 결정됩니다.
		// * numsOfPageLinks: 다시 계산된 값을 페이지 링크의 개수로 곱하여 현재 그룹의 시작 페이지 번호를 얻습니다.
		if (temp > totalLinkNo) {
			endPageNo = totalLinkNo;
		} else {
			endPageNo = temp;
		}
		
		startPageNo = ((endPageNo - 1) / numsOfPageLinks) * numsOfPageLinks + 1;
		
		if (startPageNo == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		
		if (endPageNo * criteria.getNumsPerPage() >= totalCount) {
			hasNext = false;
		} else {
			hasNext = true;
		}
		// Math.ceil 
		// Math.floor 
		
	}
	
} // end PageMaker

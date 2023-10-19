package edu.spring.mall.domain;

import java.util.Date;

public class QnaBoardVO {
	private int qnaBoardId;
	private String memberId;
	private String qnaBoardTitle;
	private String qnaBoardContent;
	private Date qnaBoardCreatedDate;
	
	
	public QnaBoardVO() {}


	public QnaBoardVO(int qnaBoardId, String memberId, String qnaBoardTitle, String qnaBoardContent,
			Date qnaBoardCreatedDate) {
		super();
		this.qnaBoardId = qnaBoardId;
		this.memberId = memberId;
		this.qnaBoardTitle = qnaBoardTitle;
		this.qnaBoardContent = qnaBoardContent;
		this.qnaBoardCreatedDate = qnaBoardCreatedDate;
	}


	public int getQnaBoardId() {
		return qnaBoardId;
	}


	public void setQnaBoardId(int qnaBoardId) {
		this.qnaBoardId = qnaBoardId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getQnaBoardTitle() {
		return qnaBoardTitle;
	}


	public void setQnaBoardTitle(String qnaBoardTitle) {
		this.qnaBoardTitle = qnaBoardTitle;
	}


	public String getQnaBoardContent() {
		return qnaBoardContent;
	}


	public void setQnaBoardContent(String qnaBoardContent) {
		this.qnaBoardContent = qnaBoardContent;
	}


	public Date getQnaBoardCreatedDate() {
		return qnaBoardCreatedDate;
	}


	public void setQnaBoardCreatedDate(Date qnaBoardCreatedDate) {
		this.qnaBoardCreatedDate = qnaBoardCreatedDate;
	}


	@Override
	public String toString() {
		return "QnaBoardVO [qnaBoardId=" + qnaBoardId + ", memberId=" + memberId + ", qnaBoardTitle=" + qnaBoardTitle
				+ ", qnaBoardContent=" + qnaBoardContent + ", qnaBoardCreatedDate=" + qnaBoardCreatedDate + "]";
	}
	
	

	
	
	
}

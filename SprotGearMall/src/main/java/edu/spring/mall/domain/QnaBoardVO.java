package edu.spring.mall.domain;

import java.util.Date;

public class QnaBoardVO {
	private int qBoardId;
	private String memberId;
	private String qBoardTitle;
	private String qBoardContent;
	private Date qBoardCreatedDate;
	
	public QnaBoardVO() {}

	public QnaBoardVO(int qBoardId, String memberId, String qBoardTitle, String qBoardContent, Date qBoardCreatedDate) {
		super();
		this.qBoardId = qBoardId;
		this.memberId = memberId;
		this.qBoardTitle = qBoardTitle;
		this.qBoardContent = qBoardContent;
		this.qBoardCreatedDate = qBoardCreatedDate;
	}

	public int getqBoardId() {
		return qBoardId;
	}

	public void setqBoardId(int qBoardId) {
		this.qBoardId = qBoardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getqBoardTitle() {
		return qBoardTitle;
	}

	public void setqBoardTitle(String qBoardTitle) {
		this.qBoardTitle = qBoardTitle;
	}

	public String getqBoardContent() {
		return qBoardContent;
	}

	public void setqBoardContent(String qBoardContent) {
		this.qBoardContent = qBoardContent;
	}

	public Date getqBoardCreatedDate() {
		return qBoardCreatedDate;
	}

	public void setqBoardCreatedDate(Date qBoardCreatedDate) {
		this.qBoardCreatedDate = qBoardCreatedDate;
	}

	@Override
	public String toString() {
		return "QnaBoard [qBoardId=" + qBoardId + ", memberId=" + memberId + ", qBoardTitle=" + qBoardTitle
				+ ", qBoardContent=" + qBoardContent + ", qBoardCreatedDate=" + qBoardCreatedDate + "]";
	}
	
	
	
	
}

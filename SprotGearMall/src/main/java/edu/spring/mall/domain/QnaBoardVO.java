package edu.spring.mall.domain;

import java.util.Date;

public class QnaBoardVO {
	private int qnaBoardId;
	private String memberId;
	private String qnaBoardTitle;
	private String qnaBoardContent;
	private Date qnaBoardCreatedDate;
	
	public QnaBoardVO() {}

	public QnaBoardVO(int qBoardId, String memberId, String qBoardTitle, String qBoardContent, Date qBoardCreatedDate) {
		super();
		this.qnaBoardId = qBoardId;
		this.memberId = memberId;
		this.qnaBoardTitle = qBoardTitle;
		this.qnaBoardContent = qBoardContent;
		this.qnaBoardCreatedDate = qBoardCreatedDate;
	}

	public int getqBoardId() {
		return qnaBoardId;
	}

	public void setqBoardId(int qBoardId) {
		this.qnaBoardId = qBoardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getqBoardTitle() {
		return qnaBoardTitle;
	}

	public void setqBoardTitle(String qBoardTitle) {
		this.qnaBoardTitle = qBoardTitle;
	}

	public String getqBoardContent() {
		return qnaBoardContent;
	}

	public void setqBoardContent(String qBoardContent) {
		this.qnaBoardContent = qBoardContent;
	}

	public Date getqBoardCreatedDate() {
		return qnaBoardCreatedDate;
	}

	public void setqBoardCreatedDate(Date qBoardCreatedDate) {
		this.qnaBoardCreatedDate = qBoardCreatedDate;
	}

	@Override
	public String toString() {
		return "QnaBoard [qnaBoardId=" + qnaBoardId + ", memberId=" + memberId + ", qnaBoardTitle=" + qnaBoardTitle
				+ ", qnaBoardContent=" + qnaBoardContent + ", qnaBoardCreatedDate=" + qnaBoardCreatedDate + "]";
	}
	
	
	
	
}

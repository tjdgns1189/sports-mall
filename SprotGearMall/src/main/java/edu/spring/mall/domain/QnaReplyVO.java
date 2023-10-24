package edu.spring.mall.domain;

import java.util.Date;

public class QnaReplyVO {
	private int qReplyId;
	private int qBoardId;
	private String memberId;
	private String qReplyContent;
	private Date qReplyCreatedDate;
	
	
	public QnaReplyVO() {}


	public QnaReplyVO(int qReplyId, int qBoardId, String memberId, String qReplyContent, Date qReplyCreatedDate) {
		super();
		this.qReplyId = qReplyId;
		this.qBoardId = qBoardId;
		this.memberId = memberId;
		this.qReplyContent = qReplyContent;
		this.qReplyCreatedDate = qReplyCreatedDate;
	}


	public int getqReplyId() {
		return qReplyId;
	}


	public void setqReplyId(int qReplyId) {
		this.qReplyId = qReplyId;
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


	public String getqReplyContent() {
		return qReplyContent;
	}


	public void setqReplyContent(String qReplyContent) {
		this.qReplyContent = qReplyContent;
	}


	public Date getqReplyCreatedDate() {
		return qReplyCreatedDate;
	}


	public void setqReplyCreatedDate(Date qReplyCreatedDate) {
		this.qReplyCreatedDate = qReplyCreatedDate;
	}


	@Override
	public String toString() {
		return "QnaReply [qReplyId=" + qReplyId + ", qBoardId=" + qBoardId + ", memberId=" + memberId
				+ ", qReplyContent=" + qReplyContent + ", qReplyCreatedDate=" + qReplyCreatedDate + "]";
	}
	
	
	
	
}

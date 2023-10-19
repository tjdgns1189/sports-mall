package edu.spring.mall.domain;

import java.util.Date;

public class QnaReplyVO {
	private int qnaReplyId;
	private int qnaBoardId;
	private String memberId;
	private String qnaReplyContent;
	private Date qnaReplyCreatedDate;
	
	
	public QnaReplyVO() {}


	public QnaReplyVO(int qReplyId, int qBoardId, String memberId, String qReplyContent, Date qReplyCreatedDate) {
		super();
		this.qnaReplyId = qReplyId;
		this.qnaBoardId = qBoardId;
		this.memberId = memberId;
		this.qnaReplyContent = qReplyContent;
		this.qnaReplyCreatedDate = qReplyCreatedDate;
	}


	public int getqReplyId() {
		return qnaReplyId;
	}


	public void setqReplyId(int qReplyId) {
		this.qnaReplyId = qReplyId;
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


	public String getqReplyContent() {
		return qnaReplyContent;
	}


	public void setqReplyContent(String qReplyContent) {
		this.qnaReplyContent = qReplyContent;
	}


	public Date getqReplyCreatedDate() {
		return qnaReplyCreatedDate;
	}


	public void setqReplyCreatedDate(Date qReplyCreatedDate) {
		this.qnaReplyCreatedDate = qReplyCreatedDate;
	}


	@Override
	public String toString() {
		return "QnaReply [qnaReplyId=" + qnaReplyId + ", qnaBoardId=" + qnaBoardId + ", memberId=" + memberId
				+ ", qnaReplyContent=" + qnaReplyContent + ", qnaReplyCreatedDate=" + qnaReplyCreatedDate + "]";
	}
	
	
	
	
}

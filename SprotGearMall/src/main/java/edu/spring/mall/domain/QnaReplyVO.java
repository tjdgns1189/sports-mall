package edu.spring.mall.domain;

import java.util.Date;

public class QnaReplyVO {
	private int qnaReplyId;
	private int qnaBoardId;
	private String memberId;
	private String qnaReplyContent;
	private Date qnaReplyCreatedDate;
	
	
	public QnaReplyVO() {}


	public QnaReplyVO(int qnaReplyId, int qnaBoardId, String memberId, String qnaReplyContent,
			Date qnaReplyCreatedDate) {
		super();
		this.qnaReplyId = qnaReplyId;
		this.qnaBoardId = qnaBoardId;
		this.memberId = memberId;
		this.qnaReplyContent = qnaReplyContent;
		this.qnaReplyCreatedDate = qnaReplyCreatedDate;
	}


	public int getQnaReplyId() {
		return qnaReplyId;
	}


	public void setQnaReplyId(int qnaReplyId) {
		this.qnaReplyId = qnaReplyId;
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


	public String getQnaReplyContent() {
		return qnaReplyContent;
	}


	public void setQnaReplyContent(String qnaReplyContent) {
		this.qnaReplyContent = qnaReplyContent;
	}


	public Date getQnaReplyCreatedDate() {
		return qnaReplyCreatedDate;
	}


	public void setQnaReplyCreatedDate(Date qnaReplyCreatedDate) {
		this.qnaReplyCreatedDate = qnaReplyCreatedDate;
	}


	@Override
	public String toString() {
		return "QnaReplyVO [qnaReplyId=" + qnaReplyId + ", qnaBoardId=" + qnaBoardId + ", memberId=" + memberId
				+ ", qnaReplyContent=" + qnaReplyContent + ", qnaReplyCreatedDate=" + qnaReplyCreatedDate + "]";
	}

	

}

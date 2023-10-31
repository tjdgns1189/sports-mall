package edu.spring.mall.domain;

import java.util.Date;

public class ReplyReplyVO {
	private int replyReplyId;
	private int qnaReplyId;
	private String memberId;
	private String replyReplyContent;
	private Date replyReplyCreatedDate;
	
	
	public ReplyReplyVO() {}


	public ReplyReplyVO(int replyReplyId, int qnaReplyId, String memberId, String replyReplyContent,
			Date replyReplyCreatedDate) {
		super();
		this.replyReplyId = replyReplyId;
		this.qnaReplyId = qnaReplyId;
		this.memberId = memberId;
		this.replyReplyContent = replyReplyContent;
		this.replyReplyCreatedDate = replyReplyCreatedDate;
	}


	public int getReplyReplyId() {
		return replyReplyId;
	}


	public void setReplyReplyId(int replyReplyId) {
		this.replyReplyId = replyReplyId;
	}


	public int getQnaReplyId() {
		return qnaReplyId;
	}


	public void setQnaReplyId(int qnaReplyId) {
		this.qnaReplyId = qnaReplyId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getReplyReplyContent() {
		return replyReplyContent;
	}


	public void setReplyReplyContent(String replyReplyContent) {
		this.replyReplyContent = replyReplyContent;
	}


	public Date getReplyReplyCreatedDate() {
		return replyReplyCreatedDate;
	}


	public void setReplyReplyCreatedDate(Date replyReplyCreatedDate) {
		this.replyReplyCreatedDate = replyReplyCreatedDate;
	}


	@Override
	public String toString() {
		return "ReplyReplyVO [replyReplyId=" + replyReplyId + ", qnaReplyId=" + qnaReplyId + ", memberId=" + memberId
				+ ", replyReplyContent=" + replyReplyContent + ", replyReplyCreatedDate=" + replyReplyCreatedDate + "]";
	}
	
	
	
	
	
}

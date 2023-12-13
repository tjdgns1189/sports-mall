package edu.spring.mall.domain;

import java.util.Date;

public class EventReplyVO {
	private int eventReplyId;
	private int eventBoardId;
	private String memberId;
	private String eventReplyContent;
	private Date eventReplyCreatedDate;
	private String userGrade; 
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public EventReplyVO() {
		super();
	}
	public EventReplyVO(int eventReplyId, int eventBoardId, String memberId, String eventReplyContent,
			Date eventReplyCreatedDate) {
		super();
		this.eventReplyId = eventReplyId;
		this.eventBoardId = eventBoardId;
		this.memberId = memberId;
		this.eventReplyContent = eventReplyContent;
		this.eventReplyCreatedDate = eventReplyCreatedDate;
	}
	@Override
	public String toString() {
		return "EventReplyVO [eventReplyId=" + eventReplyId + ", eventBoardId=" + eventBoardId + ", memberId="
				+ memberId + ", eventReplyContent=" + eventReplyContent + ", eventReplyCreatedDate="
				+ eventReplyCreatedDate + "]";
	}
	public int getEventReplyId() {
		return eventReplyId;
	}
	public void setEventReplyId(int eventReplyId) {
		this.eventReplyId = eventReplyId;
	}
	public int getEventBoardId() {
		return eventBoardId;
	}
	public void setEventBoardId(int eventBoardId) {
		this.eventBoardId = eventBoardId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEventReplyContent() {
		return eventReplyContent;
	}
	public void setEventReplyContent(String eventReplyContent) {
		this.eventReplyContent = eventReplyContent;
	}
	public Date getEventReplyCreatedDate() {
		return eventReplyCreatedDate;
	}
	public void setEventReplyCreatedDate(Date eventReplyCreatedDate) {
		this.eventReplyCreatedDate = eventReplyCreatedDate;
	}

	
	
	
	
}

package edu.spring.mall.domain;

import java.util.Date;

public class EventBoardVO {
	private int eventBoardId;
	private String memberId;
	private String eventBoardTitle;
	private String eventBoardContent;
	private Date eventBoardCreatedDate;
	private int replyCnt;
	public EventBoardVO() {}
	@Override
	public String toString() {
		return "EventBoardVO [eventBoardId=" + eventBoardId + ", memberId=" + memberId + ", eventBoardTitle="
				+ eventBoardTitle + ", eventBoardContent=" + eventBoardContent + ", eventBoardCreatedDate="
				+ eventBoardCreatedDate + ", replyCnt=" + replyCnt + "]";
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
	public String getEventBoardTitle() {
		return eventBoardTitle;
	}
	public void setEventBoardTitle(String eventBoardTitle) {
		this.eventBoardTitle = eventBoardTitle;
	}
	public String getEventBoardContent() {
		return eventBoardContent;
	}
	public void setEventBoardContent(String eventBoardContent) {
		this.eventBoardContent = eventBoardContent;
	}
	public Date getEventBoardCreatedDate() {
		return eventBoardCreatedDate;
	}
	public void setEventBoardCreatedDate(Date eventBoardCreatedDate) {
		this.eventBoardCreatedDate = eventBoardCreatedDate;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public EventBoardVO(int eventBoardId, String memberId, String eventBoardTitle, String eventBoardContent,
			Date eventBoardCreatedDate, int replyCnt) {
		super();
		this.eventBoardId = eventBoardId;
		this.memberId = memberId;
		this.eventBoardTitle = eventBoardTitle;
		this.eventBoardContent = eventBoardContent;
		this.eventBoardCreatedDate = eventBoardCreatedDate;
		this.replyCnt = replyCnt;
	}
	public EventBoardVO(int eventBoardId, String memberId, String eventBoardTitle, String eventBoardContent,
			Date eventBoardCreatedDate) {
		super();
		this.eventBoardId = eventBoardId;
		this.memberId = memberId;
		this.eventBoardTitle = eventBoardTitle;
		this.eventBoardContent = eventBoardContent;
		this.eventBoardCreatedDate = eventBoardCreatedDate;
	}
	
	
	
	
}

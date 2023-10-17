package edu.spring.mall.domain;

import java.util.Date;

public class NoticeBoardVO {
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeCreatedDate;
	
	public NoticeBoardVO() {}

	public NoticeBoardVO(int noticeId, String noticeTitle, String noticeContent, Date noticeCreatedDate) {
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCreatedDate = noticeCreatedDate;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeCreatedDate() {
		return noticeCreatedDate;
	}

	public void setNoticeCreatedDate(Date noticeCreatedDate) {
		this.noticeCreatedDate = noticeCreatedDate;
	}

	@Override
	public String toString() {
		return "NoticeBoardVO [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeCreatedDate=" + noticeCreatedDate + "]";
	}
	
	
	
	
}

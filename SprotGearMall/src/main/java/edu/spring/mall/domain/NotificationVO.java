package edu.spring.mall.domain;

import java.util.Date;

public class NotificationVO {
	private int notificationId;
	private String memberId;
	private String groupId;
	private String notificationType;
	private String notificationContent;
	private String targetUrl;
	private Date notificationDate;
	private Date readDate;
	private int entityId;
	public NotificationVO() {
	}
	public NotificationVO(int notificationId, String memberId, String groupId, String notificationType,
			String notificationContent, String targetUrl, Date notificationDate, Date readDate, int entityId) {
		this.notificationId = notificationId;
		this.memberId = memberId;
		this.groupId = groupId;
		this.notificationType = notificationType;
		this.notificationContent = notificationContent;
		this.targetUrl = targetUrl;
		this.notificationDate = notificationDate;
		this.readDate = readDate;
		this.entityId = entityId;
	}
	public NotificationVO(String memberId, String groupId, String notificationType, String notificationContent,
			String targetUrl, Date notificationDate, int entityId) {
		super();
		this.memberId = memberId;
		this.groupId = groupId;
		this.notificationType = notificationType;
		this.notificationContent = notificationContent;
		this.targetUrl = targetUrl;
		this.notificationDate = notificationDate;
		this.entityId = entityId;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getNotificationContent() {
		return notificationContent;
	}
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	@Override
	public String toString() {
		return "NotificationVO [notificationId=" + notificationId + ", memberId=" + memberId + ", groupId=" + groupId
				+ ", notificationType=" + notificationType + ", notificationContent=" + notificationContent
				+ ", targetUrl=" + targetUrl + ", notificationDate=" + notificationDate + ", readDate=" + readDate
				+ ", entityId=" + entityId + "]";
	}

}

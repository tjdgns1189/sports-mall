package edu.spring.mall.domain;

import java.util.Date;

import edu.spring.mall.websocket.ChatRoom;

public class ChatRoomVO {
	private String roomId;
    private Date createdDate;
    private String username;
	@Override
	public String toString() {
		return "ChatRoomVO [roomId=" + roomId + ", createdDate=" + createdDate + ", username=" + username + "]";
	}
	public ChatRoomVO(String roomId, Date createdDate, String username) {
		super();
		this.roomId = roomId;
		this.createdDate = createdDate;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ChatRoomVO() {
		super();
	}
	public ChatRoomVO(String roomId, Date createdDate) {
		super();
		this.roomId = roomId;
		this.createdDate = createdDate;
	}
	
	public ChatRoomVO(ChatRoom room, String username) {
	    this.roomId = room.getRoomId();
	    this.createdDate = room.getCreatedDate();
	    this.username = username;
	}

	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}

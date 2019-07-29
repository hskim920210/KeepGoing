package com.tje.model;

public class Board_Qna {
	private int board_id;
	private int topic;
	private int category;
	private String title;
	private String content;
	
	public Board_Qna() {
	}

	public Board_Qna(int board_id, int topic, int category, String title, String content) {
		this.board_id = board_id;
		this.topic = topic;
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getTopic() {
		return topic;
	}

	public void setTopic(int topic) {
		this.topic = topic;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

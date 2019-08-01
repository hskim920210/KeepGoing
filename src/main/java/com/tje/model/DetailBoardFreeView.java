package com.tje.model;

import java.util.Date;

public class DetailBoardFreeView {
	private int board_id;          // 게시판 아이디
	private int topic;			   // sql data의 board info 의 넘버링
	private int category;		   // 관심사
	private String title;          // 제목
	private String content;		   // 게시글
	
	private int comment_cnt;       // 댓글 숫자   
	private String member_id;      // 멤버 아이디
	private String nickname;       // 닉네임
	private int view_cnt;          // 게시글 읽음 카운트
	private int like_cnt;          // 좋아요 수
	private int dislike_cnt;       // 싫어요 수
	private Date write_date;       // 작성날자
		public DetailBoardFreeView() {
		}
		public DetailBoardFreeView(int board_id, int topic, int category, String title, String content, int comment_cnt,
				String member_id, String nickname, int view_cnt, int like_cnt, int dislike_cnt, Date write_date) {
			super();
			this.board_id = board_id;
			this.topic = topic;
			this.category = category;
			this.title = title;
			this.content = content;
			this.comment_cnt = comment_cnt;
			this.member_id = member_id;
			this.nickname = nickname;
			this.view_cnt = view_cnt;
			this.like_cnt = like_cnt;
			this.dislike_cnt = dislike_cnt;
			this.write_date = write_date;
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
		public int getComment_cnt() {
			return comment_cnt;
		}
		public void setComment_cnt(int comment_cnt) {
			this.comment_cnt = comment_cnt;
		}
		public String getMember_id() {
			return member_id;
		}
		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public int getView_cnt() {
			return view_cnt;
		}
		public void setView_cnt(int view_cnt) {
			this.view_cnt = view_cnt;
		}
		public int getLike_cnt() {
			return like_cnt;
		}
		public void setLike_cnt(int like_cnt) {
			this.like_cnt = like_cnt;
		}
		public int getDislike_cnt() {
			return dislike_cnt;
		}
		public void setDislike_cnt(int dislike_cnt) {
			this.dislike_cnt = dislike_cnt;
		}
		public Date getWrite_date() {
			return write_date;
		}
		public void setWrite_date(Date write_date) {
			this.write_date = write_date;
		}
		
		
		
}
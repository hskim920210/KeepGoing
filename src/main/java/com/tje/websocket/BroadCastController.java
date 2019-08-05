package com.tje.websocket;

import java.util.*;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// 보낸 사람 제외하고 나머지에게 모두 메세지를 보내는 기능
// 키 값은 웹 소켓의 세션 ID 값, 밸류는 해당 웹소켓 객체를 저장 => 모든 회원들의 정보를 갖는다.
public class BroadCastController extends TextWebSocketHandler {
	// 닉네임 설정을 위한 이너클래스. 사용자의 세션 정보와 닉네임을 저장한다.
	class WebSocketClientInfo {
		private WebSocketSession session;
		private String nickname;
		
		public WebSocketClientInfo(WebSocketSession session) {
			this.session = session;
		}

		public WebSocketSession getSession() {
			return session;
		}

		public void setSession(WebSocketSession session) {
			this.session = session;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
	}
	
	// 현제 웹 서버에 접속중인 클라이언트들의 웹소켓을 저장하는 MAP 객체 (동기화 지원)
	private Hashtable<String, WebSocketClientInfo> sessionMap = new Hashtable<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + "가 연결됨");
		// 접속된 클라이언트의 세션 객체를 저장
		sessionMap.put(session.getId(), new WebSocketClientInfo(session));
	}
	
	private void sendClientNicknames(String id) throws Exception {
		StringBuffer idAndNicknames = new StringBuffer("newClient:");
		idAndNicknames.append(String.format("%s (%s),", sessionMap.get(id).getNickname(), id));
		TextMessage message = new TextMessage(idAndNicknames.toString());
		for( String key : sessionMap.keySet() ) {
			if( !key.equals(id) ) {
				sessionMap.get(key).getSession().sendMessage(message);
			}
		}
	}
}
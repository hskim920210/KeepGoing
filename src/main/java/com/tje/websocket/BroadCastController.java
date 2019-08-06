package com.tje.websocket;

import java.util.*;
import com.tje.model.*;

import org.springframework.web.socket.CloseStatus;
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
	
	// 웹 소켓 클라이언트가 서버측으로 데이터를 전송할 때 실행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		Member login_member = (Member)map.get("login_member");
		String nickname = login_member.getNickname();
		System.out.println(nickname);
		
		// 각 세션의 첫번째 메세지 전달 코드는 닉네임
		String id = session.getId();
		// 연결 되자마자 닉네임이 바로 전달되어 닉네임 설정
		if( sessionMap.get(id).getNickname() == null ) {
			StringBuffer idAndNicknames = new StringBuffer("newClient:");
			for( String key : sessionMap.keySet() ) {
				if( key.equals(id) ) {
					continue;
				}
				idAndNicknames.append(String.format("%s (%s)", sessionMap.get(key).getNickname(), key));
			}
			session.sendMessage(new TextMessage(idAndNicknames.toString()));
			sessionMap.get(id).setNickname(message.getPayload());
			sendClientNicknames(id);
			return;
		}
		// 클라이언트가 전송한 데이터 출력
		System.out.printf("%s로부터 [%s]를 받음\n", sessionMap.get(id).getNickname(), message.getPayload());
		
		StringTokenizer st = new StringTokenizer(message.getPayload(), ":@");
		st.nextToken(); // to
		String target = st.nextToken(); // target ID
		String msg = st.nextToken(); // 메세지
		
		if( !target.equals("all") ) {
			sessionMap.get(target).getSession().sendMessage(
					new TextMessage("(" + sessionMap.get(id).getNickname() + "님으로부터 쪽지 : " + msg));
		} else {
			// 모든 클라이언트에게 전송
			for( String key : sessionMap.keySet() ) {
				sessionMap.get(key).getSession().sendMessage(new TextMessage(sessionMap.get(id).getNickname() + " : " + message.getPayload()));
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + "가 연결 종료됨");
		// 연결이 종료된 클라이언트를 세션 목록에서 제거하고 그 정보를 받아온다. (remove 리턴값)
		WebSocketClientInfo closedClient = sessionMap.remove(session.getId());
		String strClosedClient = String.format("closed:%s (%s)", closedClient.getNickname(), session.getId());
		
		for( String key : sessionMap.keySet() ) {
			sessionMap.get(key).getSession().sendMessage(new TextMessage(strClosedClient));
		}
	}
}
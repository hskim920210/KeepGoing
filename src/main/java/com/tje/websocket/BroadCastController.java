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
	
	class WebSocketClientInfo {
		private WebSocketSession session;
		private ChatMember chatMember;
		public WebSocketClientInfo() {}
		public WebSocketClientInfo(WebSocketSession session, ChatMember chatMember) {
			this.session = session;
			this.chatMember = chatMember;
		}
		public WebSocketSession getSession() {
			return session;
		}
		public void setSession(WebSocketSession session) {
			this.session = session;
		}
		public ChatMember getChatMember() {
			return chatMember;
		}
		public void setChatMember(ChatMember chatMember) {
			this.chatMember = chatMember;
		}
	}
	
	class ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public ChatMember() {}
		public ChatMember(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	
	// Admin 객체
	class Admin extends ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public Admin() {}
		public Admin(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	// Client 객체
	class Client extends ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public Client() {}
		public Client(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	
	
	
	// 현제 웹 서버에 접속중인 클라이언트들의 웹소켓을 저장하는 MAP 객체 (동기화 지원)
	private LinkedHashMap<String, WebSocketClientInfo> sessionMap = new LinkedHashMap<>();
	// Admin 상태를 저장하는 MAP 객체. 순서를 유지하기 위해 LinkedHashMap을 사용한다
	private LinkedHashMap<Admin, WebSocketClientInfo> adminMap = new LinkedHashMap<BroadCastController.Admin, WebSocketClientInfo>();
	// Client 상태를 저장하는 MAP 객체 
	private LinkedHashMap<Client, WebSocketClientInfo> clientMap = new LinkedHashMap<BroadCastController.Client, WebSocketClientInfo>();
	// Admin과 Client의 짝을 지어주는 MAP 객체
	private LinkedHashMap<WebSocketSession, WebSocketSession> chatMatch = new LinkedHashMap<WebSocketSession, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		boolean noAdmin = true;
		boolean noClient = true;
		Map<String, Object> map = session.getAttributes();
		Member login_member = (Member)map.get("login_member");
		String member_id = login_member.getMember_id(); // 잘 받아와짐
		String nickname = login_member.getNickname(); // 잘 받아와짐
		
		if(member_id.equals("admin")) {
			// admin이 접속할 시 clientMap에서 isConn이 false인 상태인 클라이언트와 같이 sessionMap에 저장한다.
			Admin admin = new Admin(nickname, false, session.getId());
			WebSocketClientInfo wscInfo = new WebSocketClientInfo(session, admin);
			adminMap.put(admin, wscInfo);
			
			for( Client c : clientMap.keySet() ) {
				if( c.isConn() == false ) {
					admin.setConn(true);
					c.setConn(true);
					sessionMap.put(admin.getSessionId(), adminMap.get(admin));
					sessionMap.put(c.getSessionId(), clientMap.get(c));
					chatMatch.put(adminMap.get(admin).getSession(), clientMap.get(c).getSession());
					chatMatch.put(clientMap.get(c).getSession(), adminMap.get(admin).getSession());
					adminMap.get(admin).getSession().sendMessage(new TextMessage("클라이언트와 연결되었습니다.\n"));
					clientMap.get(c).getSession().sendMessage(new TextMessage("관리자와 연결되었습니다.\n"));
					noClient = false;
					break;
				}
			}
			if( noClient ) {
				adminMap.get(admin).getSession().sendMessage(new TextMessage("상담 가능한 클라이언트가 없습니다.\n"));
			}
			System.out.println("admin.nickName : " + nickname +
					", admin.isConn : " + admin.isConn() + 
					", admin.sessionId : " + admin.getSessionId() + 
					", wscInfo.session : " + wscInfo.getSession() + 
					", wscInfo.chatMember : " + wscInfo.getChatMember().getClass());
			for( String s : sessionMap.keySet() ) {
				System.out.println("key : " + s +
						", val.session" + sessionMap.get(s).getSession() +
						", val.chatMember.nick" + sessionMap.get(s).getChatMember().getNickname() + 
						", val.chatMember.isConn" + sessionMap.get(s).getChatMember().isConn());
				
			}
		} else {
			// client가 접속할 시 adminMap에서 isConn이 false인 상태인 어드민과 같이 sessionMap에 저장한다.
			Client client = new Client(nickname, false, session.getId());
			WebSocketClientInfo wscInfo = new WebSocketClientInfo(session, client);
			clientMap.put(client, wscInfo);
			
			for( Admin a : adminMap.keySet() ) {
				if( a.isConn() == false ) {
					client.setConn(true);
					a.setConn(true);
					sessionMap.put(client.getSessionId(), clientMap.get(client));
					sessionMap.put(a.getSessionId(), adminMap.get(a));
					chatMatch.put(clientMap.get(client).getSession(), adminMap.get(a).getSession());
					chatMatch.put(adminMap.get(a).getSession(), clientMap.get(client).getSession());
					clientMap.get(client).getSession().sendMessage(new TextMessage("관리자와 연결되었습니다.\n"));
					adminMap.get(a).getSession().sendMessage(new TextMessage("클라이언트와 연결되었습니다.\n"));
					noAdmin = false;
					break;
				}
			}
			if( noAdmin ) {
				clientMap.get(client).getSession().sendMessage(new TextMessage("상담 가능한 관리자가 없습니다."));
			}
			System.out.println("client.nickName : " + nickname +
					", client.isConn : " + client.isConn() + 
					", client.sessionId : " + client.getSessionId() + 
					", wscInfo.session : " + wscInfo.getSession() + 
					", wscInfo.chatMember : " + wscInfo.getChatMember().getClass());
			for( String s : sessionMap.keySet() ) {
				System.out.println("key : " + s +
						", val.session" + sessionMap.get(s).getSession() +
						", val.chatMember.nick" + sessionMap.get(s).getChatMember().getNickname() + 
						", val.chatMember.isConn" + sessionMap.get(s).getChatMember().isConn());
				
			}
		}
	}
	
	// 웹 소켓 클라이언트가 서버측으로 데이터를 전송할 때 실행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		/*
		// 연결 되자마자 닉네임이 바로 전달되어 닉네임 설정
		String id = session.getId();
		if( sessionMap.get(id).getNickname() == null ) {
			sessionMap.get(id).setNickname(nickname); // 해당 세션의 WebSocketClientInfo를 완성했다.
			StringBuffer idAndNicknames = new StringBuffer("newClient:");
			idAndNicknames.append(String.format("%s (%s)", nickname, id)); // (닉네임) (세션아이디) 형태로 저장. 접속할때마다 append
			session.sendMessage(new TextMessage(idAndNicknames.toString()));
			sendClientNicknames(id);
			return;
		}
		// 닉네임이 설정된 이후
		// 클라이언트가 전송한 데이터 출력
		 * */
		// System.out.printf("%s로부터 [%s]를 받음\n", client.getNickname(), message.getPayload());
		WebSocketSession receiverSession = chatMatch.get(session);
		//StringTokenizer st = new StringTokenizer(message.getPayload(), ": @");
		StringTokenizer st = new StringTokenizer(message.getPayload(), ": ");
		// st.nextToken(); // to
		String sender = st.nextToken();
		System.out.println(sender);
		String receiver = sessionMap.get(receiverSession.getId()).getChatMember().getNickname();
		System.out.println(receiver);
		String msg = st.nextToken(); // 메세지
		System.out.println(msg);
		receiverSession.sendMessage(new TextMessage(sender + " : " + msg));
		/*
		if( !target.equals("all") ) {
			sessionMap.get(target).getSession().sendMessage(
					new TextMessage("(" + sessionMap.get(id).getNickname() + "님으로부터 쪽지 : " + msg));
		} else {
			// 모든 클라이언트에게 전송
			for( String key : sessionMap.keySet() ) {
				sessionMap.get(key).getSession().sendMessage(new TextMessage(sessionMap.get(id).getNickname() + " : " + message.getPayload()));
			}
		}
		*/
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + "가 연결 종료됨");
		// 연결이 종료된 클라이언트를 세션 목록에서 제거하고 그 정보를 받아온다. (remove 리턴값)
		WebSocketClientInfo closedClient = sessionMap.remove(session.getId());
		/*
		String strClosedClient = String.format("closed:%s (%s)", closedClient.getNickname(), session.getId());
		
		for( String key : sessionMap.keySet() ) {
			sessionMap.get(key).getSession().sendMessage(new TextMessage(strClosedClient));
		}
		*/
	}
}
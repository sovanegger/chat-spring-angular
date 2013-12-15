package cz.prf.uai.tomsovsky.chat.dao;

import java.util.List;

import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;

public interface ChatMessageDao extends DomainDao<ChatMessage> {
	public List<ChatMessage> findChatMessagesByRoomId(long roomId, int limit);
	public List<ChatMessage> findChatMessagesByUsername(String username, int limit);
	public List<ChatMessage> findChatMessagesByByRoomIdAndUsername(long roomId, String username, int limit);
}

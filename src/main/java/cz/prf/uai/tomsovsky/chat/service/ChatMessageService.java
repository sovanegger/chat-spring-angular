package cz.prf.uai.tomsovsky.chat.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.prf.uai.tomsovsky.chat.dao.ChatMessageDao;
import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;

@Service(value="chatMessageService")
public class ChatMessageService {
	@Autowired
	private ChatMessageDao chatMessageDao;
	
	@Transactional
	public List<ChatMessage> getAllChatMessages(Integer limitConstraint) {
		int limit = limitConstraint != null ? limitConstraint.intValue() : -1;
		return chatMessageDao.list(limit);
	}
	
	@Transactional 
	public List<ChatMessage> getChatMessagesByConstraints(Long roomIdConstraint, String sentFromConstraint, Integer limitConstraint) {
		List<ChatMessage> chatMessages = null;
		long roomId = roomIdConstraint != null ? roomIdConstraint.longValue() : -1;
		String username = sentFromConstraint != null ? sentFromConstraint : "";
		int limit = limitConstraint != null ? limitConstraint.intValue() : -1;
		
		if (roomId > 0 && !username.equals(""))
			chatMessages = chatMessageDao.findChatMessagesByByRoomIdAndUsername(roomId, username, limit);
		else if (roomId > 0)
			chatMessages = chatMessageDao.findChatMessagesByRoomId(roomId, limit);
		else if (!username.equals(""))
			chatMessages = chatMessageDao.findChatMessagesByUsername(username, limit);
		else
			chatMessages = chatMessageDao.list(limit);
		
		return chatMessages;
	}
	
	@Transactional
	public ChatMessage getChatMessageById(long id) {
		return chatMessageDao.getById(id);
	}
	
	@Transactional
	public ChatMessage saveChatMessage(ChatMessage chatMessage) {
		chatMessage.setSentOn(new DateTime());
		Long chatMessageId = chatMessageDao.save(chatMessage);
		return chatMessageDao.getById(chatMessageId);
	}
	
	@Transactional
	public void updateChatMessage(ChatMessage chatMessage) {
		chatMessageDao.update(chatMessage);
	}
	
	@Transactional
	public void updateChatMessage(ChatMessage chatMessage, long chatMessageId) {
		if (chatMessage.getId() <= 0)
			chatMessage.setId(chatMessageId);
		chatMessageDao.update(chatMessage);
	}
	
	@Transactional
	public void deleteChatMessage(ChatMessage chatMessage) {
		chatMessageDao.delete(chatMessage);
	}
	
	@Transactional
	public void deleteChatMessageById(long id) {
		ChatMessage chatMessage = chatMessageDao.getById(id);
		chatMessageDao.delete(chatMessage);
	}

	public ChatMessageDao getChatMessageDao() {
		return chatMessageDao;
	}
	public void setChatMessageDao(ChatMessageDao chatMessageDao) {
		this.chatMessageDao = chatMessageDao;
	}
}

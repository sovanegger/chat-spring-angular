package cz.prf.uai.tomsovsky.chat.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.prf.uai.tomsovsky.chat.dao.ChatMessageDao;
import cz.prf.uai.tomsovsky.chat.dao.RoomDao;
import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;
import cz.prf.uai.tomsovsky.chat.domain.Room;

@Service
public class BootstrapService {
	@Autowired
	private ChatMessageDao chatMessageDao;
	@Autowired 
	private RoomDao roomDao;
	
	@Transactional
	public void initDatabaseByDummyDates() {
		Room roomOne = new Room("Motor České Budějovice");
		roomOne.addChatMessage(new ChatMessage("kotalik", "Počkej, nehrál ty jsi ve Varech?", new DateTime(2013, 12, 12, 22, 10))).
			addChatMessage(new ChatMessage("skuhravy", "Ahoj, já jsem Venca Skuhravý a budu za Vás hrát.", new DateTime(2013, 12, 12, 22, 9))).
			addChatMessage(new ChatMessage("kotalik", "Ahoj, jmenuji se Aleš Kotalík a jsem kapitán.", new DateTime(2013, 12, 12, 22, 8))).
			addChatMessage(new ChatMessage("skuhravy", "Jo.", new DateTime(2013, 12, 12, 22, 11))).
			addChatMessage(new ChatMessage("kotalik", "No tak to je degradace, jít do první ligy.", new DateTime(2013, 12, 12, 22, 12)));
		roomDao.save(roomOne);
		
		Room roomTwo = new Room("Soci");
		roomTwo.addChatMessage(new ChatMessage("jagr", "Tak v tom případě ok. Počítej se mnou.", new DateTime(2013, 12, 12, 22, 12))).
			addChatMessage(new ChatMessage("jagr", "A budu moct zase nést vlajku?", new DateTime(2013, 12, 12, 22, 10))).
			addChatMessage(new ChatMessage("hadamczik", "Čau Jardo, pojedeš na olympiádu?", new DateTime(2013, 12, 12, 22, 9))).
			addChatMessage(new ChatMessage("hadamczik", "No jasný...", new DateTime(2013, 12, 12, 22, 11)));
		roomDao.save(roomTwo);
	}

	public ChatMessageDao getChatMessageDao() {
		return chatMessageDao;
	}
	public void setChatMessageDao(ChatMessageDao chatMessageDao) {
		this.chatMessageDao = chatMessageDao;
	}

	public RoomDao getRoomDao() {
		return roomDao;
	}
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
}

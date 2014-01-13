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
		Room roomOne = new Room("Mustafar");
		roomOne.addChatMessage(new ChatMessage("Anakin Skywalker", "YOU WILL NOT TAKE HER FROM ME!", new DateTime(2013, 12, 12, 22, 10))).
			addChatMessage(new ChatMessage("Obi Wan Kenobi", "You have done that yourself!", new DateTime(2013, 12, 12, 22, 9))).
			addChatMessage(new ChatMessage("Anakin Skywalker", "You've turned her against me!", new DateTime(2013, 12, 12, 22, 8))).
			addChatMessage(new ChatMessage("Obi Wan Kenobi", "Anakin, my allegiance is to the Republic! To democracy!!", new DateTime(2013, 12, 12, 22, 15))).
			addChatMessage(new ChatMessage("Anakin Skywalker", "If you're not with me, then you're my enemy!", new DateTime(2013, 12, 12, 22, 16))).
			addChatMessage(new ChatMessage("Obi Wan Kenobi", "Your new Empire?", new DateTime(2013, 12, 12, 22, 13))).
			addChatMessage(new ChatMessage("Anakin Skywalker", "Don't make me kill you.", new DateTime(2013, 12, 12, 22, 14))).
			addChatMessage(new ChatMessage("Obi Wan Kenobi", "Only a Sith deals in absolutes. I will do what I must.", new DateTime(2013, 12, 12, 22, 17))).
			addChatMessage(new ChatMessage("Anakin Skywalker", "You will try.", new DateTime(2013, 12, 12, 22, 18))).
			addChatMessage(new ChatMessage("Obi Wan Kenobi", "Your anger and your "
					+ "lust for power have already done that. You have allowed this Dark Lord "
					+ "to twist your mind, until now...until now you have become the very thing "
					+ "you swore to destroy.", new DateTime(2013, 12, 12, 22, 11))).
			addChatMessage(new ChatMessage("Anakin Skywalker", " Don't lecture me, Obi-Wan! "
					+ "I see through the lies of the Jedi! I do not fear the Dark Side as you do! "
					+ "I have brought peace, freedom, justice, and security to my new Empire!", new DateTime(2013, 12, 12, 22, 12)));
		roomDao.save(roomOne);
		
		Room roomTwo = new Room("Coruscant");
		roomTwo.addChatMessage(new ChatMessage("Yoda", "If so powerful you are... why leave?", new DateTime(2013, 12, 12, 22, 12))).
			addChatMessage(new ChatMessage("Yoda", "Faith in your new apprentice, misplaced may be. As is your faith in the dark side of the Force.", new DateTime(2013, 12, 12, 22, 14))).
			addChatMessage(new ChatMessage("Darth Sidious", "You will not stop me! Darth Vader will become more powerful than either of us!", new DateTime(2013, 12, 12, 22, 13))).
			addChatMessage(new ChatMessage("Yoda", "Not if anything to say about it I have!", new DateTime(2013, 12, 12, 22, 10))).
			addChatMessage(new ChatMessage("Darth Sidious", "I have waited a long time for this moment, my little green friend. At last, the Jedi are no more.", new DateTime(2013, 12, 12, 22, 9))).
			addChatMessage(new ChatMessage("Yoda", "At an end your rule is, and not short enough was it.", new DateTime(2013, 12, 12, 22, 11)));
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

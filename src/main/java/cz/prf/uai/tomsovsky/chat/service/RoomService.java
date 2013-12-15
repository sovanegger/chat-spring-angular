package cz.prf.uai.tomsovsky.chat.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.prf.uai.tomsovsky.chat.dao.RoomDao;
import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;
import cz.prf.uai.tomsovsky.chat.domain.Room;

@Service(value="roomService")
public class RoomService {
	@Autowired
	private RoomDao roomDao;
	
	@Transactional
	public List<Room> getAllRooms(Integer limitConstraint) {
		int limit = limitConstraint != null ? limitConstraint.intValue() : -1;
		return roomDao.list(limit);
	}
	
	@Transactional
	public Room getRoomById(long id) {
		return roomDao.getById(id);
	}
	
	@Transactional
	public List<ChatMessage> getRoomChatMessages(long roomId) {
		Room room = roomDao.getById(roomId);
		List<ChatMessage> chatMessages = room.getChatMessages();
		Hibernate.initialize(chatMessages);
		return chatMessages;
	}
	
	@Transactional
	public Room saveRoom(Room room) {
		Long roomId = roomDao.save(room);
		return roomDao.getById(roomId);
	}
	
	@Transactional
	public void updateRoom(Room room) {
		roomDao.update(room);
	}
	
	@Transactional
	public void updateRoom(Room room, long roomId) {
		if (room.getId() <= 0)
			room.setId(roomId);
		roomDao.update(room);
	}
	
	@Transactional
	public void deleteRoom(Room room) {
		roomDao.delete(room);
	}
	
	@Transactional
	public void deleteRoomById(long id) {
		Room room = roomDao.getById(id);
		roomDao.delete(room);
	}

	public RoomDao getRoomDao() {
		return roomDao;
	}
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
}

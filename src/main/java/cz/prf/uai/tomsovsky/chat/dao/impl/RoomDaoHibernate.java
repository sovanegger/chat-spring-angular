package cz.prf.uai.tomsovsky.chat.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cz.prf.uai.tomsovsky.chat.dao.RoomDao;
import cz.prf.uai.tomsovsky.chat.domain.Room;

@Repository(value="roomDao")
public class RoomDaoHibernate extends DaoHibernate implements RoomDao {
	@Override
	public Long save(Room room) {
		Session session = currentSession();
		long id = (Long)session.save(room);
		return id;
	}

	@Override
	public void update(Room room) {
		currentSession().update(room);
	}

	@Override
	public Room getById(long id) {
		return (Room) currentSession().get(Room.class, id);
	}

	@Override
	public void delete(Room room) {
		currentSession().delete(room);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> list(int limit) {
		List<Room> rooms = null;
		if (limit > 0) {
			// TODO dodelat limit
			rooms = currentSession().createCriteria(Room.class).list();
		}
		else
			rooms = currentSession().createCriteria(Room.class).list();
		return rooms;
	}
}

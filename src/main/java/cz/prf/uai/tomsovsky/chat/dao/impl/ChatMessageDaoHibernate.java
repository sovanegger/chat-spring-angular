package cz.prf.uai.tomsovsky.chat.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.prf.uai.tomsovsky.chat.dao.ChatMessageDao;
import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;

@Repository(value="chatMessageDao")
public class ChatMessageDaoHibernate extends DaoHibernate implements
		ChatMessageDao {

	@Override
	public Long save(ChatMessage chatMessage) {
		return (Long)currentSession().save(chatMessage);
	}

	@Override
	public void update(ChatMessage chatMessage) {
		currentSession().update(chatMessage);
	}

	@Override
	public ChatMessage getById(long id) {
		return (ChatMessage) currentSession().get(ChatMessage.class, id);
	}

	@Override
	public void delete(ChatMessage chatMessage) {
		currentSession().delete(chatMessage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> list(int limit) {
		return currentSession().createCriteria(ChatMessage.class).
				setMaxResults(limit).addOrder(Order.desc("sentOn")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> findChatMessagesByRoomId(long roomId, int limit) {
		Criteria criteria = currentSession().createCriteria(ChatMessage.class);
		criteria.add(Restrictions.eq("room.id", roomId)).
				setMaxResults(limit).addOrder(Order.desc("sentOn"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> findChatMessagesByUsername(String username, int limit) {
		Criteria criteria = currentSession().createCriteria(ChatMessage.class);
		criteria.add(Restrictions.eq("username", username)).
				setMaxResults(limit).addOrder(Order.desc("sentOn"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> findChatMessagesByByRoomIdAndUsername(long roomId, String username, int limit) {
		Criteria criteria = currentSession().createCriteria(ChatMessage.class);
		criteria.add(Restrictions.eq("room.id", roomId)).add(Restrictions.eq("username", username)).
				setMaxResults(limit).addOrder(Order.desc("sentOn"));
		return criteria.list();
	}
}

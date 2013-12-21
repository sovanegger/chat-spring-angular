package cz.prf.uai.tomsovsky.chat.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_rooms")
public class Room implements IDomain {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="Název místnosti nesmí být prázdný")
	@NotNull(message="Název místnosti nesmí být prázdný")
	@Column(nullable=false)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="room")
	@OrderBy("sentOn DESC")
	@JsonIgnore
	private List<ChatMessage> chatMessages;
	
	public Room() {}
	
	public Room(String name) {
		this.name = name;
	}
	
	public List<ChatMessage> getChatMessages() {
		if (this.chatMessages == null)
			this.chatMessages = new ArrayList<ChatMessage>();
		return chatMessages;
	}
	public void setChatMessages(List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}
	
	/**
	 * Adds a domain instance into List collection. If List collection is not set, 
	 * than the method itself creates instance of ArrayList collection.
	 * 
	 * @param chatMessage ChatMessage instance which will be added to ArrayList collection.
	 * @return	Returns instance of Room.
	 */
	public Room addChatMessage(ChatMessage chatMessage) {
		if (this.chatMessages == null)
			this.chatMessages = new ArrayList<ChatMessage>();
		chatMessage.setRoom(this);
		this.chatMessages.add(chatMessage);
		return this;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
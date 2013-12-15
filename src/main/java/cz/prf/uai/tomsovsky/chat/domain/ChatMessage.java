package cz.prf.uai.tomsovsky.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="tb_chatmessages")
public class ChatMessage implements IDomain {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="Jméno nesmí být prázdné")
	@NotNull(message="Jméno nesmí být prázdné")
	@Column(nullable=false)
	private String username;
	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String messageText;
	
	@Column(nullable=false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime sentOn;
	
	@ManyToOne
	@JoinColumn(name="room_id", nullable=false)
	@JsonProperty
	private Room room;
	
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	
	public ChatMessage() {}
	
	public ChatMessage(String name, String messageText, DateTime sentOn) {
		this.username = name;
		this.messageText = messageText;
		this.sentOn = sentOn;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	public DateTime getSentOn() {
		return sentOn;
	}
	public void setSentOn(DateTime sentOn) {
		this.sentOn = sentOn;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}

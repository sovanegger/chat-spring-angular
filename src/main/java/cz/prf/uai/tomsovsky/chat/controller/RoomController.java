package cz.prf.uai.tomsovsky.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;
import cz.prf.uai.tomsovsky.chat.domain.Room;
import cz.prf.uai.tomsovsky.chat.service.RoomService;

@Controller
public class RoomController {
	 @Autowired
	 private RoomService roomService;
	 
	/**
	 * Returns room by its id, which is sent to the method in an URI path.
	 * 
	 * @param roomId Identifier of room.
	 * @return Returns room in a specified format.
	 */
	@RequestMapping(value="/rooms/{roomId}", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Room show(@PathVariable long roomId) {
		return roomService.getRoomById(roomId);
	}
	
	/**
	 * Deletes room by its id, which is sent to the method in an URI path.
	 * 
	 * @param roomId Identifier of room.
	 */
	@RequestMapping(value="/rooms/{roomId}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable long roomId) {
		this.roomService.deleteRoomById(roomId);
	}
	
	/**
	 * Saves new room instance. Checks if there are some validation errors.
	 * 
	 * @param room New Room instance.
	 * @param bindingResult Results of validation.
	 * @return Returns id of new created instance.
	 */
	@RequestMapping(value="/rooms", method=RequestMethod.POST, consumes="application/json", produces = "application/json; charset=utf-8")
	@ResponseStatus(value=HttpStatus.CREATED)
	@ResponseBody
	public Room save( @RequestBody @Valid Room room ) {
		return roomService.saveRoom(room);
	}
	
	/**
	 * Edits room instance sent in http request body. Checks if there are some validation errors.
	 * 
	 * @param room Edited room instance.
	 * @param bindingResult Results of validation.
	 */
	@RequestMapping(value="/rooms/{roomId}", method=RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public void update( @PathVariable long roomId, @RequestBody @Valid Room room ) {
		roomService.updateRoom(room, roomId);
	}
	
	/**
	 * Edits room instance sent in http request body. Checks if there are some validation errors.
	 * 
	 * @param room Edited room instance.
	 * @param bindingResult Results of validation.
	 */
	@RequestMapping(value="/rooms", method=RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public void update( @RequestBody @Valid Room room ) {
		roomService.updateRoom(room);
	}
	
	/**
	 * Returns all rooms.
	 * 
	 * @return Returns a collection of rooms.
	 */
	@RequestMapping(value="/rooms", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Room> list(@RequestParam(value="limit", required=false) Integer limit) {
		return roomService.getAllRooms(limit);
	}
	
	@RequestMapping(value="/rooms/{roomId}/chatmessages", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public List<ChatMessage> listRoomChatMessages(@PathVariable long roomId) {
		return roomService.getRoomChatMessages(roomId);
	}

	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
}

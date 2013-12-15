package cz.prf.uai.tomsovsky.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cz.prf.uai.tomsovsky.chat.domain.ChatMessage;
import cz.prf.uai.tomsovsky.chat.service.ChatMessageService;

@Controller
public class ChatMessageController {
	@Autowired
	private ChatMessageService chatMessageService;
	
	/**
	 * Returns chatMessage by its id, which is sent to the method in an URI path.
	 * 
	 * @param chatMessageId Identifier of chatMessage.
	 * @return Returns chatMessage in a specified format.
	 */
	@RequestMapping(value="/chatmessages/{chatMessageId}", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ChatMessage show(@PathVariable long chatMessageId) {
		return chatMessageService.getChatMessageById(chatMessageId);
	}
	
	/**
	 * Deletes chatMessage by its id, which is sent to the method in an URI path.
	 * 
	 * @param chatMessageId Identifier of chatMessage.
	 */
	@RequestMapping(value="/chatmessages/{chatMessageId}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable long chatMessageId) {
		this.chatMessageService.deleteChatMessageById(chatMessageId);
	}
	
	/**
	 * Saves new chatMessage instance. Checks if there are some validation errors.
	 * 
	 * @param chatMessage New ChatMessage instance.
	 * @param bindingResult Results of validation.
	 * @return Returns id of new created instance.
	 */
	@RequestMapping(value="/chatmessages", method=RequestMethod.POST, consumes="application/json", produces = "application/json; charset=utf-8")
	@ResponseStatus(value=HttpStatus.CREATED)
	@ResponseBody
	public ChatMessage save( @RequestBody @Valid ChatMessage chatMessage ) {
		return chatMessageService.saveChatMessage(chatMessage);
	}
	
	/**
	 * Edits chatMessage instance sent in http request body. Checks if there are some validation errors.
	 * 
	 * @param chatMessage Edited chatMessage instance.
	 * @param bindingResult Results of validation.
	 */
	@RequestMapping(value="/chatmessages/{chatMessageId}", method=RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public void update( @PathVariable long chatMessageId, @RequestBody @Valid ChatMessage chatMessage, BindingResult bindingResult ) {
		chatMessageService.updateChatMessage(chatMessage, chatMessageId);
	}
	
	/**
	 * Edits chatMessage instance sent in http request body. Checks if there are some validation errors.
	 * 
	 * @param chatMessage Edited chatMessage instance.
	 * @param bindingResult Results of validation.
	 */
	@RequestMapping(value="/chatmessages", method=RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public void update( @RequestBody @Valid ChatMessage chatMessage ) {
		chatMessageService.updateChatMessage(chatMessage);
	}
	
	/**
	 * Returns chatMessages depending on arguments.
	 * 
	 * @param roomId Room id constraint.
	 * @param username Username constraint.
	 * @param limit Limit constraint.
	 * @return Returns chatMessages depending on arguments.
	 */
	@RequestMapping(value="/chatmessages", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<ChatMessage> list(@RequestParam(value="roomId", required=false) Long roomId,
									@RequestParam(value="sentFrom", required=false) String sentFrom,
									@RequestParam(value="limit", required=false) Integer limit) {
		return chatMessageService.getChatMessagesByConstraints(roomId, sentFrom, limit);
	}
}

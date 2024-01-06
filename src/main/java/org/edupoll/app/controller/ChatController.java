package org.edupoll.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.edupoll.app.model.Account;
import org.edupoll.app.model.ChatMessage;
import org.edupoll.app.model.ChatRoom;
import org.edupoll.app.model.Product;
import org.edupoll.app.repository.ChatsRepository;
import org.edupoll.app.repository.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	private final ChatsRepository chatsRepository;
	private final ProductsRepository productsRepository;

	
	// 참여중인 채팅방 목록 보여주는 핸들러
	@GetMapping("/list") 
	public String showJoinedChatRooms(@SessionAttribute Account logonAccount, Model model) {
		List<ChatRoom> chatRooms = chatsRepository.findChatRoomByBuyerIdOrSellerId(logonAccount.getId());
		
		model.addAttribute("chatRooms", chatRooms);
		
		return "chat/list";
	}
	
	
	// 특정 상품에 문의채팅방으로 연결 요청을 처리할 핸들러
	@GetMapping("/link")
	public String proceedLinkToChatRoom(@RequestParam int productId, @SessionAttribute Account logonAccount) {
		Product product = productsRepository.findById(productId);
		if (product == null || product.getAccountId().equals(logonAccount.getId())) {
			return "chat/link/error";
		}

		Map<String, Object> criteria = new HashMap<>();
		criteria.put("productId", productId);
		criteria.put("buyerId", logonAccount.getId());

		ChatRoom chatRoom = chatsRepository.findChatRoomByProductIdAndBuyerId(criteria);
		if (chatRoom != null) {
			return "redirect:/chat/room/" + chatRoom.getId();
		} else {
			// 8-4-4-4-12
			String id = UUID.randomUUID().toString().split("-")[4].toUpperCase();
			ChatRoom newChatRoom = new ChatRoom();
			newChatRoom.setId(id);
			newChatRoom.setProductId(productId);
			newChatRoom.setSellerId(product.getAccountId());
			newChatRoom.setBuyerId(logonAccount.getId());
			chatsRepository.saveChatRoom(newChatRoom);

			return "redirect:/chat/room/" + id;
		}

	}

	// 특정방에 입장을 처리할 매핑
	@GetMapping("/room/{id}")
	public String showChatRoom(@PathVariable String id, @SessionAttribute Account logonAccount, Model model) {
		ChatRoom chatRoom = chatsRepository.findChatRoomById(id);

		if (chatRoom == null || (!logonAccount.getId().equals(chatRoom.getSellerId())
				&& !logonAccount.getId().equals(chatRoom.getBuyerId()))) {
			return "chat/error";
		}

		Product product = productsRepository.findById(chatRoom.getProductId());
		List<ChatMessage> messages = chatsRepository.findChatMessageByRoomId(id);

		Map<String, Object> criteria = new HashMap<>();
		criteria.put("roomId", id);
		criteria.put("logonAccountId", logonAccount.getId());
		chatsRepository.updateCheckAtByRoomId(criteria);

		model.addAttribute("chatRoom", chatRoom);
		model.addAttribute("product", product);
		model.addAttribute("chatMessages", messages);

		return "chat/room";
	}

	
	// 메시지 등록 처리하는 핸들러
	@ResponseBody
	@PostMapping("/room/{roomId}/message")
	public String proceedAddChatMessage(@SessionAttribute Account logonAccount,
			@PathVariable String roomId, @RequestParam String content
			) {
		
		ChatMessage messages = ChatMessage.builder()	//
					.chatRoomId(roomId).talkerId(logonAccount.getId()).content(content).build();
		
		chatsRepository.saveChatMessage(messages);
		
		Gson gson = new Gson();
		Map<String, Object> response = new HashMap<>();
		response.put("result", "success");
		
		return gson.toJson(response);
	}
	
	
	// 특정 시점이후의 메시지 목록을 전송하는 핸들러
	
	@GetMapping(
			path = "/room/{roomId}/latest",
			produces = "text/plain;charset=utf-8"
			)
	@ResponseBody
	public String proceedFindLatestMessage(@PathVariable String roomId, 
			@RequestParam int lastMessageId, @SessionAttribute Account logonAccount) {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("roomId", roomId);
		criteria.put("lastMessageId", lastMessageId);
		List<ChatMessage> list = chatsRepository.findAfterChatMessageByRoomId(criteria);
		
		criteria.clear();
		criteria.put("roomId", roomId);
		criteria.put("logonAccountId", logonAccount.getId());
		chatsRepository.updateCheckAtByRoomId(criteria);

		
		
		Map<String, Object> response = new HashMap<>();
		response.put("result", "success");
		response.put("messages", list);
		Gson gson = new Gson();
		return gson.toJson(response);
	}
	
}


















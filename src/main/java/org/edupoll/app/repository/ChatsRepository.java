package org.edupoll.app.repository;

import java.util.List;
import java.util.Map;

import org.edupoll.app.model.ChatMessage;
import org.edupoll.app.model.ChatRoom;
import org.edupoll.app.model.ProductIdAndBuyerId;

public interface ChatsRepository {
	public int saveChatRoom(ChatRoom chatRoom);
	public ChatRoom findChatRoomByProductIdAndBuyerId(Map<String, Object> criteria);
	public int saveChatMessage(ChatMessage chatMessage);
	public ChatRoom findChatRoomById(String id);
	public List<ChatRoom> findChatRoomByBuyerIdOrSellerId(String logonAccountId);
	public List<ChatMessage> findChatMessageByRoomId(String roomId);
	public List<ChatMessage> findAfterChatMessageByRoomId(Map<String, Object> criteria);
	public int countUncheckedMessageByRoomId(Map<String, Object> criteria);
	public int updateCheckAtByRoomId(Map<String, Object> criteria);
}

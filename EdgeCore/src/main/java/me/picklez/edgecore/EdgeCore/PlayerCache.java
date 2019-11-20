package me.picklez.edgecore.EdgeCore;

import java.util.UUID;

import me.picklez.edgecore.config.SimpleConfig;

public class PlayerCache {

	private final SimpleConfig cfg;

	private long lastLoginTime;
	private String lastChatMessage;

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastChatMessages() {
		return lastChatMessage;
	}

	public void setLastChatMessages(String lastChatMessages) {
		this.lastChatMessage = lastChatMessages;
	}

	public PlayerCache(UUID id) {
		cfg = new SimpleConfig("cache.dat", false);
		cfg.setPathPrefix(id.toString());
		onLoad();
	}

	private void onLoad() {
		lastLoginTime = cfg.getLong("last-login-time", 0L);
		lastChatMessage = cfg.getString("last-chat-message", "");
	}

	public void save() {
		if((lastLoginTime != 0)) {
			cfg.set("last-login-time", lastLoginTime);
		}
		if(!(lastChatMessage.equals(""))) {
			cfg.set("last-chat-message", lastChatMessage);
		}
	}

}

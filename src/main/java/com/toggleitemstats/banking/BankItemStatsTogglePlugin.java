package com.toggleitemstats.banking;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Bank Item Stats Toggle"
)
public class BankItemStatsTogglePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BankItemStatsToggleConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Bank Item Stats Toggle started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Bank Item Stats Toggle stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Bank Item Stats Toggle says " + config.greeting(), null);
		}
	}

	@Provides
	BankItemStatsToggleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankItemStatsToggleConfig.class);
	}
}

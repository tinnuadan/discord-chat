package net.tinnuadan.discordchat;

import net.fabricmc.api.ModInitializer;
import net.tinnuadan.discordchat.discord.Bot;
import net.tinnuadan.discordchat.minecraft.MCMessageSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;

public class DiscordChatMod implements ModInitializer
{
  public static Bot bot = null;
  public static final String LOG_ID = "DiscordChat";
  public static final String MOD_ID = "discordchat";
//  public static final String DISCORD_PREFIX = "$16571354$";

  public static final Logger LOGGER = LogManager.getLogger(LOG_ID);

  private MCMessageSender mcSender = null;

  @Override
  public void onInitialize() {
    final String channelID = "";
    final String token = "";
    mcSender = new MCMessageSender();
    LOGGER.info("Starting DiscordChat");
    try {
      bot = new Bot(token, channelID, mcSender);
    } catch (LoginException e) {
      bot = null;
      e.printStackTrace();
    }


    LOGGER.info("DiscordChat initialised");
    System.out.println("DiscordChat initialised");

  }
}

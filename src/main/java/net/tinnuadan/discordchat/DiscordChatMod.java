package net.tinnuadan.discordchat;

import net.fabricmc.api.ModInitializer;
import net.tinnuadan.discordchat.discord.Bot;
import net.tinnuadan.discordchat.minecraft.MCMessageSender;

import javax.security.auth.login.LoginException;

public class DiscordChatMod implements ModInitializer
{
  public static Bot bot = null;

  private MCMessageSender mcSender = null;

  @Override
  public void onInitialize() {
    final String channelID = "765936461467746304";
    final String token = "NzY1OTM2OTIzMjI2OTMxMjEx.X4cEWA.uxMDMZC4BdlAqxXdoBlSPPVe90M";
    mcSender = new MCMessageSender();
    try {
      bot = new Bot(token, channelID, mcSender);
    } catch (LoginException e) {
      bot = null;
      e.printStackTrace();
    }


    System.out.println("DiscordChat initialised");

  }
}

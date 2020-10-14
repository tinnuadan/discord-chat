package net.tinnuadan.discordchat.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.tinnuadan.discordchat.common.IMessageSender;

import javax.security.auth.login.LoginException;

public class Bot
{
  private final JDA jda;

  public Bot(String token, String channelID, IMessageSender mcMessageSender) throws LoginException {
    jda = JDABuilder.createDefault(token)
      .addEventListeners(new MessageListener(channelID, mcMessageSender))
      .build();
  }

  public void sendMessage(String message)
  {
    //todo: send to discord
  }
}

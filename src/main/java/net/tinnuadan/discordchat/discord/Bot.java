package net.tinnuadan.discordchat.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.tinnuadan.discordchat.DiscordChatMod;
import net.tinnuadan.discordchat.common.IMessageSender;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class Bot
{
  private final JDA jda;
  private final String channelID;

  public Bot(String token, String channelID, IMessageSender mcMessageSender) throws LoginException {
    this.channelID = channelID;
    jda = JDABuilder.createDefault(token)
      .addEventListeners(new MessageListener(channelID, mcMessageSender))
      .build();
  }

  public void sendMessage(String message)
  {
    DiscordChatMod.LOGGER.info("Sending message to discord");
    DiscordChatMod.LOGGER.info(message);
    
    TextChannel channel = jda.getTextChannelById(channelID);
    assert channel != null;
    channel.sendMessage(message).queue();
  }
}

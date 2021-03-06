package net.tinnuadan.discordchat.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.tinnuadan.discordchat.DiscordChatMod;
import net.tinnuadan.discordchat.common.IMessageSender;

import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class Bot
{
  private final JDA jda;
  private final String channelID;

  private Instant lastMessageTime = Instant.now();
  private String lastMessage = null;

  public Bot(String token, String channelID, IMessageSender mcMessageSender) throws LoginException {
    this.channelID = channelID;
    jda = JDABuilder.createDefault(token)
      .addEventListeners(new MessageListener(channelID, mcMessageSender))
      .build();
  }

  public void sendMessage(String message)
  {
    if(lastMessage != null && message.endsWith(lastMessage))
    {
      Duration diff = Duration.between(Instant.now(), lastMessageTime);
      if(diff.abs().getSeconds() < 3)
      {
        return;
      }
    }
    DiscordChatMod.LOGGER.info("Sending message to discord");
    DiscordChatMod.LOGGER.info(message);
    
    TextChannel channel = jda.getTextChannelById(channelID);
    assert channel != null;
    channel.sendMessage(message).queue();
  }

  public void setLastReceivedMessage(String message)
  {
    lastMessage = message;
    lastMessageTime = Instant.now();
  }
}

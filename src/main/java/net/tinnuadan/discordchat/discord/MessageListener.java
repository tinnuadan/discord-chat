package net.tinnuadan.discordchat.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.tinnuadan.discordchat.common.IMessageSender;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter
{
  final String channelID;
  final IMessageSender msgSender;

  MessageListener(String channelID, IMessageSender msgSender)
  {
    this.channelID = channelID;
    this.msgSender = msgSender;
  }

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    if(event.getAuthor().isBot()) {
      return;
    }
    if(!event.isFromGuild()) {
      return;
    }
    if(!event.getChannel().getId().equals(channelID)) {
      return;
    }
    msgSender.sendMessage(event.getMessage().toString());
  }
}

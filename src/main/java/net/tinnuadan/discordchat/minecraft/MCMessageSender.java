package net.tinnuadan.discordchat.minecraft;

import net.minecraft.client.MinecraftClient;
import net.tinnuadan.discordchat.common.IMessageSender;

public class MCMessageSender implements IMessageSender
{
  private static final MinecraftClient mc = MinecraftClient.getInstance();

  @Override
  public void sendMessage(String msg) {
    assert mc.player != null;
    mc.player.sendChatMessage(msg);
  }
}

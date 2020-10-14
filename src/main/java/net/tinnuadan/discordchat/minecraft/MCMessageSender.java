package net.tinnuadan.discordchat.minecraft;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.tinnuadan.discordchat.common.IMessageSender;
import net.minecraft.util.Identifier;

public class MCMessageSender implements IMessageSender
{
  private static final MinecraftClient mc = MinecraftClient.getInstance();
  private static final Identifier DISCORD_CHAT_MSG = new Identifier("net.tinnuadan.discordchat","discordmsg");
  private static final int MAGIC_NUMBER = 16571354;

  public MCMessageSender() {
    ServerSidePacketRegistry.INSTANCE.register(DISCORD_CHAT_MSG, ((packetContext, attachedData) -> {
      int magicNumber = attachedData.readInt();
      String payload = attachedData.readString();
      packetContext.getTaskQueue().execute(() -> {
        if(magicNumber == MAGIC_NUMBER)
        {
          packetContext.getPlayer().sendMessage(Text.of(payload), false);
        }
      });
    }));
  }

  @Override
  public void sendMessage(String msg) {
    PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
    data.writeInt(MAGIC_NUMBER);
    data.writeString(msg);
    ClientSidePacketRegistry.INSTANCE.sendToServer(DISCORD_CHAT_MSG, data);
  }
}

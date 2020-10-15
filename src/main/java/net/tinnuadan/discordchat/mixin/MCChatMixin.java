package net.tinnuadan.discordchat.mixin;


import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tinnuadan.discordchat.DiscordChatMod;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ServerPlayNetworkHandler.class)
public class MCChatMixin {

  @Shadow public ServerPlayerEntity player;

  @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"), method = "onGameMessage")
  public void onGameMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
    if(DiscordChatMod.bot != null)
    {
      final String chatMessage = StringUtils.normalizeSpace(packet.getChatMessage());
      final String playerName = this.player.getName().asString();
      final String msg = String.format("%s: %s", playerName, chatMessage);
      DiscordChatMod.bot.sendMessage(msg);
    }
  }

}
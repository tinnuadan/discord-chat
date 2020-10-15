package net.tinnuadan.discordchat.mixin;

import net.fabricmc.fabric.impl.client.indigo.IndigoMixinConfigPlugin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.tinnuadan.discordchat.DiscordChatMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class MCMessageMixin {

  @Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;)V")
  private void addMessage(Text message, CallbackInfo info) {
    DiscordChatMod.LOGGER.info("addMessage");
    if(message instanceof TranslatableText)
    {
      final TranslatableText trmsg = (TranslatableText) message;
      if(!trmsg.getKey().equals("chat.type.text"))
      {
        DiscordChatMod.LOGGER.info(message.getString());
        DiscordChatMod.bot.sendMessage(message.getString());
      }
    }
  }
}

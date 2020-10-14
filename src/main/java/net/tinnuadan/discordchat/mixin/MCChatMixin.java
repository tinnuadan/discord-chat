package net.tinnuadan.discordchat.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.tinnuadan.discordchat.DiscordChatMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MCChatMixin
{
  @Inject(at = @At("HEAD"), method = "sendMessage(Ljava/lang/String;Z)V", cancellable = true)
  private void sendMessage(String text, boolean showInHistory, CallbackInfo info) {
    // if it was not send from discord
    if(!text.endsWith("§0§f"))
    {
      if(DiscordChatMod.bot != null)
      {
        DiscordChatMod.bot.sendMessage(text);
      }
    }

  }
}

package net.tinnuadan.discordchat.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot
{
  private final JDA jda;

  public Bot(String token) throws LoginException {
    jda = JDABuilder.createDefault(token).build();
  }


}

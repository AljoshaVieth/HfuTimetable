package de.aljoshavieth.hfutimetable;

import de.aljoshavieth.hfutimetable.discord.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

/** Created by Aljosha on 16.02.2019 */
public class Main {

  public static void main(String[] args) {
    try {
      JDA api =
          new JDABuilder("NTQ2NDMwNDg4ODYwOTUwNTI4.D0oHmA.cvmGYP3NsMFY3v9-6ZrCLss_FNE").build();
      api.addEventListener(new MessageListener());
    } catch (LoginException e) {
      e.printStackTrace();
    }

  }
}

package de.aljoshavieth.hfutimetable.discord;

import de.aljoshavieth.hfutimetable.HfuEvent;
import de.aljoshavieth.hfutimetable.TimeTableManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Created by Aljosha on 16.02.2019 */
public class MessageListener extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    MessageChannel channel = event.getChannel();
    if (event.getAuthor().isBot()) return;
    // We don't want to respond to other bot accounts, including ourself
    Message message = event.getMessage();
    String content = message.getContentRaw();
    String[] args = content.split("\\s");
    // getContentRaw() is an atomic getter
    // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip
    // discord formatting)
    if (args[0].equalsIgnoreCase("!hfubot")) {
      TimeTableManager timeTableManager = new TimeTableManager();
      Date date = null;
      try {
        date = new SimpleDateFormat("dd.MM.yyyy").parse(args[1]);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("calendar.ics").getFile());
        timeTableManager.getEventsPerDate(date, file);

        for (HfuEvent hfuEvent : timeTableManager.getEventsPerDate(date, file)) {
          Message eventMessage =
              new MessageBuilder()
                  .setEmbed(
                      new EmbedBuilder()
                          .setColor(new Color(243312))
                          .setFooter(hfuEvent.getProf(), null)
                          .setAuthor(hfuEvent.getName(), null, null)
                          .addField(hfuEvent.getTimePeriod(), hfuEvent.getLocation(), false)
                          .build())
                  .build();
          channel.sendMessage(eventMessage).queue();
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (content.equals("!ping")) {
      channel
          .sendMessage("Pong!")
          .queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
    }
  }
}

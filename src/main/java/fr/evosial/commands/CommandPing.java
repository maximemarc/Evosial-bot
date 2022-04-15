package fr.evosial.commands;

import fr.evosial.utils.commandTools.CommandExecutor;
import fr.evosial.utils.commandTools.Commands;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;


public class CommandPing implements CommandExecutor
    {
    @Override
    public void run(MessageCreateEvent event, Commands cmd, String[] args)
        {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Ping.....")
                .setAuthor("Maxime")
                .setColor(Color.ORANGE);
        TextChannel channel = event.getChannel();
        new MessageBuilder()
                .addEmbed(embed)
                .addComponents
                    (
                    ActionRow.of(Button.success("success", "Send a message"),
                    Button.danger("danger", "Delete this message"),
                    Button.secondary("secondary", "Remind me after 5 minutes"),
                    Button.link("https://music.youtube.com/watch?v=gdnzYaTcypw&list=PLRBp0Fe2GpglKIXdvLnzcnCdRwEr3tbkO","go")))
                    .send(channel).thenAccept(message ->
                        {
                        long unixBot = message.getCreationTimestamp().toEpochMilli();
                        long unixUser = event.getMessage().getCreationTimestamp().toEpochMilli();
                        long ping = unixBot - unixUser;
                        embed.setColor(Color.GREEN)
                             .setTitle("Pong!!!!")
                             .setDescription("**" + ping + " ms**");
                        message.edit(embed);
                        }
                    );
        }
    }

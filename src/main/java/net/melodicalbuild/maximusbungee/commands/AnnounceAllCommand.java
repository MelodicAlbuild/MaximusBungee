package net.melodicalbuild.maximusbungee.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.protocol.packet.Chat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class AnnounceAllCommand extends Command {

    public AnnounceAllCommand() {
        super("announceall", "maximusbungee.admin", "aall");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /announceall <message>"));
            return;
        }
        // Here, we're looping through each argument.

        StringBuilder message = new StringBuilder();

        for (String arg : args) {
            message.append(arg).append(" ");
        }

        ProxyServer server = ProxyServer.getInstance();
        server.broadcast(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', String.valueOf(message))));

        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Message has been sent to all servers!"));
    }
}

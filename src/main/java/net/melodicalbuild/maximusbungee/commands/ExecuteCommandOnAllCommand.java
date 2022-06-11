package net.melodicalbuild.maximusbungee.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class ExecuteCommandOnAllCommand extends Command{

    public ExecuteCommandOnAllCommand()  {
        super("executeall", "maximusbungee.admin", "eall");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /executeall <command>"));
            return;
        }
        // Here, we're looping through each argument.

        StringBuilder cmd = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            cmd.append(args[i]).append(" ");
        }

        Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();

        for (Entry<String, ServerInfo> en : servers.entrySet()) { // Looping through each Server of Bungee.
            String name = en.getKey();
            ServerInfo all = ProxyServer.getInstance().getServerInfo(name);
            sendToBukkit(cmd.toString(), all); // "command" is a sub-channel which will be used to determine the message is sent by this plugin.
        }

        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Command: '/" + cmd + "' has been executed on all servers."));
    }

// Method to send message to Bukkit.

    private void sendToBukkit(String message, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF("command");
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Note the "Return". It is the channel name that we registered in our Main class of Bungee plugin.
        server.sendData("melodicalbuild:return", stream.toByteArray());

    }



}
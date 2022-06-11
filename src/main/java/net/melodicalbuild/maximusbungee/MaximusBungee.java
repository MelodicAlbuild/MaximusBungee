package net.melodicalbuild.maximusbungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.melodicalbuild.maximusbungee.commands.AnnounceAllCommand;
import net.melodicalbuild.maximusbungee.commands.ExecuteCommandOnAllCommand;
import net.melodicalbuild.maximusbungee.listeners.PluginMessageReceiver;

public final class MaximusBungee extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AnnounceAllCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ExecuteCommandOnAllCommand());
        ProxyServer.getInstance().registerChannel("melodicalbuild:return");
        ProxyServer.getInstance().registerChannel("melodicalbuild:send");

        ProxyServer.getInstance().getPluginManager().registerListener(this, new PluginMessageReceiver());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package net.melodicalbuild.maximusbungee.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessageReceiver implements Listener {
    @EventHandler
    public void on(PluginMessageEvent event) {
        if ( !event.getTag().equalsIgnoreCase( "melodicalbuild:send" ) )
        {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput( event.getData() );
        try {
            String sub = in.readUTF(); // Sub-Channel
            if (sub.equals("command")) { // As in bungee part we gave the sub-channel name "command", here we're checking it sub-channel really is "command", if it is we do the rest of code.
                String cmd = in.readUTF(); // Command we gave in Bungee part.
                ProxyServer.getInstance().getLogger().info("[Maximus Bungee] Received a command message from " +
                    "Spigot," +
                    " executing it.");
                ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(),
                        cmd); // Executing the command!!

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package de.karotte128.unpredictor.util;


import de.karotte128.unpredictor.Unpredictor;
import net.kyori.adventure.text.Component;
import org.bukkit.Server;

public class Debug {
    private static final Server server = Unpredictor.getInstance().getServer();

    public static Boolean debug = false;

    public static void debugMessage(String string) {
        if (debug) {
            server.broadcast(Component.text("§eDEBUG: " + string));
        }
    }
}

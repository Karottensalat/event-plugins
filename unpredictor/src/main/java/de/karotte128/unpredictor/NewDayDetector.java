package de.karotte128.unpredictor;

import net.kyori.adventure.text.Component;
import org.bukkit.Server;

public class NewDayDetector implements Runnable {
    @Override
    public void run() {
        Server server = Unpredictor.getInstance().getServer();

        if (server.getWorld("world").getTime() == 1) {
            server.broadcast(Component.text("§cNew Day!"));
        }
    }
}

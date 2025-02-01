package foundation.esoteric.minecraft.plugins.mail.parrot.event;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import foundation.esoteric.minecraft.plugins.mail.parrot.ParrotMailPlugin;
import foundation.esoteric.minecraft.plugins.mail.parrot.parrot.BundlePositioner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class TickEndListener implements Listener {
    private final ParrotMailPlugin plugin;

    public TickEndListener() {
        this.plugin = ParrotMailPlugin.getInstance();
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTickEnd(ServerTickEndEvent event) {
        BundlePositioner.getActivePositioners().forEach(Runnable::run);
    }

    @EventHandler
    public void onTickStart(ServerTickStartEvent event) {
        BundlePositioner.getActivePositioners().removeIf(BundlePositioner::isCancelled);
        BundlePositioner.getActivePositioners().forEach(BundlePositioner::runTickStartTasks);
    }
}

package foundation.esoteric.minecraft.plugins.mail.parrot.event;

import foundation.esoteric.minecraft.plugins.mail.parrot.ParrotMailPlugin;
import foundation.esoteric.minecraft.plugins.mail.parrot.parrot.MailParrot;
import org.bukkit.entity.Parrot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public final class ParrotRemoveListener implements Listener {
    private final ParrotMailPlugin plugin;

    public ParrotRemoveListener() {
        this.plugin = ParrotMailPlugin.getInstance();
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onParrotDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Parrot parrot)) {
            return;
        }

        MailParrot mailParrot = MailParrot.from(parrot);

        if (mailParrot != null) {
            mailParrot.remove();
        }
    }

    @EventHandler
    @SuppressWarnings("removal")
    public void onParrotRemove(org.bukkit.event.entity.EntityRemoveEvent event) {
        if (!(event.getEntity() instanceof Parrot parrot)) {
            return;
        }

        if (event.getCause() == org.bukkit.event.entity.EntityRemoveEvent.Cause.UNLOAD) {
            return;
        }

        MailParrot mailParrot = MailParrot.from(parrot);
        if (mailParrot == null) {
            return;
        }

        mailParrot.remove();
    }
}

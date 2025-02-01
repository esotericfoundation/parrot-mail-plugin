package foundation.esoteric.minecraft.plugins.mail.parrot.parrot.data;

import foundation.esoteric.minecraft.plugins.mail.parrot.parrot.MailParrot;
import foundation.esoteric.minecraft.plugins.mail.parrot.parrot.journey.JourneyData;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

public record SerializedParrotData(
  String base64Encoded,
  JourneyData journeyData,
  UUID worldId,
  UUID uuid
) {
    public void markAsMailParrot() {
        MailParrot.from(this);
    }

    public World getWorld() {
        return Bukkit.getWorld(worldId);
    }
}

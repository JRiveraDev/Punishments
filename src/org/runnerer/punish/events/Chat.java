package org.runnerer.punish.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import org.bukkit.event.player.PlayerLoginEvent;
import org.runnerer.punish.PunishmentSystem;
import org.runnerer.punish.utils.F;

public class Chat implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e)
    {
        if (!(PunishmentSystem.hasBeenPunished(e.getPlayer()
                .getUniqueId().toString())))
            return;

        if (PunishmentSystem.isMuted(e.getPlayer()
                .getUniqueId().toString()))
        {
            if (!(PunishmentSystem.getCurrentMute(e.getPlayer()
                    .getUniqueId().toString()).isActive()))
                return;

            String reason = PunishmentSystem.getCurrentMute(e
                    .getPlayer().getUniqueId().toString()).getReason();
            String time = PunishmentSystem.getCurrentMute(e
                    .getPlayer().getUniqueId().toString()).getTimePunishedFor();
            e.getPlayer().sendMessage(F.main("Punish", "You are currently muted for " + reason + ", for " + time + "."));
        }
    }
}
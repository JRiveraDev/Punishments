package org.runnerer.punish.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.runnerer.punish.PunishmentSystem;

public class Login implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e)
    {
        if (!(PunishmentSystem.hasBeenPunished(e.getPlayer()
                .getUniqueId().toString())))
            return;

        if (PunishmentSystem.isBanned(e.getPlayer()
                .getUniqueId().toString()))
        {
            if (!(PunishmentSystem.getCurrentBan(e.getPlayer()
                    .getUniqueId().toString()).isActive()))
                return;

            String reason = PunishmentSystem.getCurrentBan(e
                    .getPlayer().getUniqueId().toString()).getReason();
            String time = PunishmentSystem.getCurrentBan(e
                    .getPlayer().getUniqueId().toString()).getTimePunishedFor();
            e.disallow(Result.KICK_BANNED, ChatColor.RED + "" + ChatColor.BOLD + "You are banned from this server for " + ChatColor.RED + "" + ChatColor.BOLD + time + "" + ChatColor.WHITE + "\n" + reason + "\n" + ChatColor.GRAY + "\n Appeal at " + ChatColor.GRAY + "www.test.com");
        }
    }
}
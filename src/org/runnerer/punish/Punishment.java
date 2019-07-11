package org.runnerer.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.runnerer.punish.utils.F;

public class Punishment
{

    PunishmentType type;

    Player player;
    String UUID;

    String reason;
    String staffname;

    String player_name;
    PunishmentSev severity;

    @SuppressWarnings("deprecation")
    public Punishment(String player, PunishmentType type, PunishmentSev sev, String staffName)
    {
        this.UUID = Bukkit.getOfflinePlayer(player).getUniqueId().toString();
        this.severity = sev;
        this.type = type;
        this.player = Bukkit.getPlayer(player);
        this.staffname = staffName;
        player_name = player;
    }

    public void activate()
    {
        PunishmentSystem.punishPlayer(UUID, player_name, staffname, severity, type, reason);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            if (p.hasPermission("punish.mod"))
            {
                if (Bukkit.getPlayer(staffname) != p)
                {
                    p.sendMessage(F
                            .main("Punish Log", staffname + " punished " + player_name + " for " + reason + ", severity being " + severity + "."));
                }
            }
        }
        if (!type.equals(PunishmentType.CHAT))
        {

        } else
        {
            try
            {
                player.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You have been muted because " + ChatColor.GRAY + reason + ChatColor.GRAY + " by " + staffname + ChatColor.GRAY + ".");
            }
            catch (Exception e)
            {

            }
        }
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}

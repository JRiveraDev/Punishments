package org.runnerer.punish.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.runnerer.punish.PunishmentManager;
import org.runnerer.punish.PunishmentSystem;
import org.runnerer.punish.utils.F;

import java.util.List;

public class HistoryCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args)
    {
        if (!(commandSender instanceof Player)) return false;

        Player caller = (Player)commandSender;

        if (args == null || args.length == 0)
        {
            PunishmentManager.openGUI(caller, caller.getName());
            return true;
        }

        if (args.length == 1)
        {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            Player onlinePlayer =  Bukkit.getPlayer(args[0]);

            if (player == null && onlinePlayer == null)
            {
                caller.sendMessage(ChatColor.BLUE + "History>" + ChatColor.GRAY + " No player found!");
            }

            if (caller.hasPermission("punish.history"))
            {

                if (caller.hasPermission("punish.historyip"))
                {
                    try
                    {

                        PunishmentManager.openGUI(caller, args[0], true);

                    }
                    catch (Exception e)
                    {
                        caller.sendMessage(F.main("History", "Player has no history."));
                    }
                } else if (caller.hasPermission("punish.history"))
                {
                    try
                    {

                        PunishmentManager.openGUI(caller, args[0]);

                    }
                    catch (Exception e)
                    {
                        caller.sendMessage(F.main("History", "Player has no history."));
                    }
                }
                return true;
            }

        } else
        {

            PunishmentManager.openGUI(caller, caller.getName());
        }
        return true;
    }

}
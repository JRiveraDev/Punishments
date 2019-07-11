package org.runnerer.punish.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.runnerer.punish.PunishmentSystem;
import org.runnerer.punish.ui.InventoryClick;
import org.runnerer.punish.ui.PunishGUIManager;
import org.runnerer.punish.utils.F;

import java.util.List;

public class PunishCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args)
    {
        if (!(commandSender instanceof Player)) return false;

        Player caller = (Player)commandSender;

        if (args == null || args.length == 0)
        {
            caller.sendMessage(ChatColor.BLUE + "Punish>" + ChatColor.GRAY + " /punish <player> <reason>");
            return true;
        }

        if (args.length < 2)
        {
            caller.sendMessage(ChatColor.BLUE + "Punish>" + ChatColor.GRAY + " /punish <player> <reason>");
            return true;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        Player onlinePlayer =  Bukkit.getPlayer(args[0]);

        if (player == null && onlinePlayer == null)
        {
            caller.sendMessage(ChatColor.BLUE + "Punish>" + ChatColor.GRAY + " No player found!");
        }

        InventoryClick.Reason = F.combine(args, 1);
        PunishGUIManager.createPunish(caller, args[0], Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
        return true;
    }

}
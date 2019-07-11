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
import org.runnerer.punish.utils.F;

public class NetworkKickCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args)
    {
        if (!(commandSender instanceof Player)) return false;

        Player caller = (Player)commandSender;

        if (args == null || args.length == 0)
        {
            caller.sendMessage(ChatColor.BLUE + "Network Removal>" + ChatColor.GRAY + " Error!");
            return true;
        }

        if (args.length < 1)
        {
            caller.sendMessage(ChatColor.BLUE + "Network Removal>" + ChatColor.GRAY + " Error!");
            return true;
        }
        if (args.length > 1)
        {
            caller.sendMessage(ChatColor.BLUE + "Network Removal>" + ChatColor.GRAY + " Error!");
            return true;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        Player onlinePlayer =  Bukkit.getPlayer(args[0]);

        if (player == null && onlinePlayer == null)
        {
            caller.sendMessage(ChatColor.BLUE + "Network Removal>" + ChatColor.GRAY + " No player found!");
        }

        caller.sendMessage(F
                .main("Network Removal", "Removing " + ChatColor.GREEN + args[0] + ChatColor.GRAY + "."));
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(args[0]);
        out.writeUTF("Your connection to the network has been lost. Please try again.");
        caller.sendPluginMessage(PunishmentSystem.instance, "BungeeCord", out.toByteArray());

        return true;
    }
}
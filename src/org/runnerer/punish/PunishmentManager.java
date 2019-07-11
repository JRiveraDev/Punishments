package org.runnerer.punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PunishmentManager
{

    public static HashMap<String, Punishment> waitingForPunishReason = new HashMap<String, Punishment>();
    public static HashMap<String, PastPunishment> waitingForPunishRemoveReason = new HashMap<>();

    public static void addNewWaitingForPunishment(String staff, Punishment p)
    {
        waitingForPunishReason.put(staff, p);
    }

    public static void gotRemoveReason(String staff, String reason)
    {
        waitingForPunishRemoveReason.get(staff).remove(reason, staff);
        waitingForPunishRemoveReason.remove(staff);
    }

    public static Punishment getPunishmet(String staff)
    {
        return waitingForPunishReason.get(staff);
    }

    public static void openGUI(Player caller, String args)
    {
        @SuppressWarnings("deprecation")
        Inventory PunishGUI = Bukkit.createInventory(null, 6 * 9, Bukkit
                .getOfflinePlayer(args).getName() + "'s History");
        {

            int space = 0;
            List<ItemStack> items = org.runnerer.punish.ui.PunishGUI.getPunishments(args);

            for (int i = 150; i >= 0; i--)
            {
                try
                {
                    items.get(i);
                }
                catch (Exception e)
                {
                    continue;
                }

                PunishGUI.setItem(space, items.get(i));
                if (space == 53)
                    break;

                space++;
            }
        }
        caller.openInventory(PunishGUI);
    }

    @SuppressWarnings("deprecation")
    public static void openGUI(Player caller, String args, boolean ip) throws ClassNotFoundException, SQLException
    {
        Inventory PunishGUI = Bukkit.createInventory(null, 6 * 9, Bukkit
                .getOfflinePlayer(args).getName() + "'s IP History");
        {

            int space = 0;

            List<ItemStack> items = org.runnerer.punish.ui.PunishGUI.getPunishmentsV2(args);

            for (int i = 150; i >= 0; i--)
            {
                try
                {
                    items.get(i);
                }
                catch (Exception e)
                {
                    continue;
                }

                PunishGUI.setItem(space, items.get(i));
                if (space == 53)
                    break;

                space++;
            }
        }
        caller.openInventory(PunishGUI);
    }

    public void addWaitingForRemoveReason(String staff, PastPunishment pp)
    {
        this.waitingForPunishRemoveReason.put(staff, pp);
    }

    public void removeCompletedPunishment(String staff)
    {
        this.waitingForPunishReason.remove(staff);
    }

    public void destroy()
    {
        this.waitingForPunishReason.clear();
        this.waitingForPunishReason = null;
        this.waitingForPunishRemoveReason.clear();
        this.waitingForPunishRemoveReason = null;
    }

}

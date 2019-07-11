package org.runnerer.punish.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.runnerer.punish.PastPunishment;
import org.runnerer.punish.PunishmentSystem;
import org.runnerer.punish.database.MySQL;
import org.runnerer.punish.ui.items.PunishCustomItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PunishGUI
{

    public static String playerUUID;
    public static Inventory PunishHistoryGUI = Bukkit
            .createInventory(null, 6 * 9, "History");
    public String AccountsPlayerName;
    public String staffname;
    boolean is_open = true;

    public Inventory PunishmentGUI = Bukkit
            .createInventory(null, 6 * 9, "Punish");
    public Inventory TraineePunishmentGUI = Bukkit
            .createInventory(null, 6 * 9, "Punish");
    public Inventory DevPunishmentGUI = Bukkit
            .createInventory(null, 6 * 9, "Punish");
    
    {

        PunishmentGUI.setItem(10, PunishCustomItems.mute());
        PunishmentGUI.setItem(12, PunishCustomItems.game());
        PunishmentGUI.setItem(14, PunishCustomItems.hacking());
        // TODO mis PunishmentGUI.setItem(7, PunishCustomItems.);
        PunishmentGUI.setItem(19, PunishCustomItems.mute_sev1());
        PunishmentGUI.setItem(21, PunishCustomItems.game_sev1());
        PunishmentGUI.setItem(23, PunishCustomItems.hacking_sev1());

        PunishmentGUI.setItem(28, PunishCustomItems.mute_sev2());

        PunishmentGUI.setItem(32, PunishCustomItems.hacking_sev2());

        // TODO warn item 25

        PunishmentGUI.setItem(37, PunishCustomItems.mute_sev3());
        PunishmentGUI.setItem(41, PunishCustomItems.hacking_sev3());

        // TODO misc mute 34

        PunishmentGUI.setItem(25, PunishCustomItems.mute_sev4());
        PunishmentGUI.setItem(34, PunishCustomItems.hacking_sev4());

    }

    {

        TraineePunishmentGUI.setItem(10, PunishCustomItems.mute());
        TraineePunishmentGUI.setItem(12, PunishCustomItems.game());
        TraineePunishmentGUI.setItem(14, PunishCustomItems.hacking());
        // TODO mis PunishmentGUI.setItem(7, PunishCustomItems.);
        TraineePunishmentGUI.setItem(19, PunishCustomItems.mute_sev1());
        TraineePunishmentGUI.setItem(21, PunishCustomItems.game_sev1());
        TraineePunishmentGUI.setItem(23, PunishCustomItems.hacking_sev1());

        TraineePunishmentGUI.setItem(28, PunishCustomItems.mute_sev2());
    }

    {

        DevPunishmentGUI.setItem(10, PunishCustomItems.mute());
        DevPunishmentGUI.setItem(12, PunishCustomItems.game());
        DevPunishmentGUI.setItem(14, PunishCustomItems.hacking());
        // TODO mis PunishmentGUI.setItem(7, PunishCustomItems.);
        DevPunishmentGUI.setItem(19, PunishCustomItems.mute_sev1());
        DevPunishmentGUI.setItem(21, PunishCustomItems.game_sev1());
        DevPunishmentGUI.setItem(23, PunishCustomItems.hacking_sev1());
        DevPunishmentGUI.setItem(28, PunishCustomItems.mute_sev2());
        DevPunishmentGUI.setItem(40, PunishCustomItems.devalert());
    }
    
    {

        int space = 0;
        List<ItemStack> items = this.getPunishments();

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

            PunishHistoryGUI.setItem(space, items.get(i));
            if (space == 53)
                break;

            space++;
        }
    }

    @SuppressWarnings("static-access")
    public PunishGUI(String account, String Staffname, String id)
    {
        this.AccountsPlayerName = account;
        this.staffname = Staffname;
        PunishGUI.playerUUID = id;


        if (PunishmentSystem.hasBeenPunished(Bukkit.getOfflinePlayer(AccountsPlayerName).getUniqueId().toString()))
        {
            int space = 45;
            List<ItemStack> items = this.getPunishments();

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

                PunishmentGUI.setItem(space, items.get(i));
                TraineePunishmentGUI.setItem(space, items.get(i));
                DevPunishmentGUI.setItem(space, items.get(i));
                if (space == 53)
                    break;

                space++;
            }
        }
    }

    public static List<ItemStack> getPunishments(String args)
    {
    	List<ItemStack> items = new ArrayList<>();
    	if (Bukkit.getPlayer(args) == null)
    	{
        
        if (!PunishmentSystem.hasBeenPunished(Bukkit.getOfflinePlayer(args).getUniqueId().toString()))
        {
            return items;
        }

        for (PastPunishment p : PunishmentSystem
                .getAllPunishmentsFromUUID(Bukkit.getOfflinePlayer(args).getUniqueId().toString()))
        {
            items.add(p.buildItem());
        }
    	} else
    	{
            if (!PunishmentSystem.hasBeenPunished(Bukkit.getPlayer(args).getUniqueId().toString()))
            {
                return items;
            }

            for (PastPunishment p : PunishmentSystem
                    .getAllPunishmentsFromUUID(Bukkit.getPlayer(args).getUniqueId().toString()))
            {
                items.add(p.buildItem());
            }
    	}
        return items;
    }

    public static List<ItemStack> getPunishmentsV2(String args) throws ClassNotFoundException, SQLException
    {
        List<ItemStack> items = new ArrayList<>();

        String uuid = Bukkit.getOfflinePlayer(args).getUniqueId().toString();
        ResultSet res = MySQL.Instance
                .querySQL("SELECT * FROM ipinfopunishments WHERE uuid = '" + uuid + "';");
        res.next();
        String ipPunished = res.getString(2);

        for (PastPunishment p : PunishmentSystem
                .getAllPunishmentsFromPunishedIP(ipPunished))
        {
        	try {
            items.add(p.buildItem());
        	} catch (Exception e)
        	{
        		
        	}
        }
        return items;
    }

    public List<ItemStack> getPunishments()
    {
        List<ItemStack> items = new ArrayList<>();
        if (!PunishmentSystem.hasBeenPunished(Bukkit.getOfflinePlayer(AccountsPlayerName).getUniqueId().toString()))
        {
            return items;
        }

        for (PastPunishment p : PunishmentSystem
                .getAllPunishmentsFromUUID(Bukkit.getOfflinePlayer(AccountsPlayerName).getUniqueId().toString()))
        {
            items.add(p.buildItem());
        }
        return items;
    }

    public void justClosed()
    {
        this.is_open = false;

        {
            Bukkit.getScheduler()
                    .scheduleSyncDelayedTask(PunishmentSystem.instance, new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            if (is_open == false)
                            {

                                PunishGUIManager
                                        .removeGUI(staffname);
                            }
                        }
                    }, 5);
        }
    }

    public void justOpened()
    {
        this.is_open = true;
    }

}

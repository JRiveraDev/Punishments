package org.runnerer.punish;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PastPunishment
{

    PunishmentType type;

    String UUID;

    String reason;
    String staffname;

    int id;

    String removeReason;
    String removeStaffName;

    boolean Activated;

    Timestamp start;
    Timestamp end;

    Timestamp now;

    PunishmentSev severity;

    /**
     * @param ID
     * @param p
     * @param type
     * @param reason
     * @param staffName
     * @param remove
     * @param Active
     */

    public PastPunishment(int ID, String player_uuid, PunishmentType type, String reason, String staffName, String remove, String removeStaff, boolean Active, Timestamp active, Timestamp ends, PunishmentSev sev)
    {

        if (remove != null)
            this.removeReason = remove;
        if (removeStaff != null)
            this.removeStaffName = removeStaff;

        this.severity = sev;
        this.start = active;
        this.end = ends;
        this.Activated = Active;
        this.id = ID;
        this.type = type;
        this.reason = reason;
        this.staffname = staffName;
        this.UUID = player_uuid;

        now = new Timestamp(System.currentTimeMillis());

        if (this.hasExpired() == true)
            PunishmentSystem
                    .removePunishment(ID, true, null, null);
    }

    public boolean isActive()
    {
        this.hasExpired();
        return PunishmentSystem.isActive(id);

    }

    public void remove(String reason, String staff)
    {
        PunishmentSystem
                .removePunishment(this.id, false, reason, staff);
    }

    public boolean hasExpired()
    {

        if (start.getTime() == end.getTime() || severity
                .equals(PunishmentSev.SEV_4))
            return false;

        if (Activated == false)
            return true;

        if (now.after(end))
        {
            Activated = false;
            PunishmentSystem
                    .removePunishment(id, true, null, null);
            return true;
        }

        Activated = true;
        return false;
    }

    public ItemStack buildItem()
    {
        ItemStack i = null;
        switch (type)
        {
            case CHAT:
                i = new ItemStack(Material.BOOK);
                break;
            case GAMEPLAY:
                i = new ItemStack(Material.REDSTONE_COMPARATOR);
                break;
            case HACKING:
                i = new ItemStack(Material.GOLD_SWORD);
                break;
        }

        hasExpired();

        ItemMeta meta = i.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (type.toString().toLowerCase().equalsIgnoreCase("chat"))
        {
            meta.setDisplayName(ChatColor.GRAY + "Type: " + ChatColor.GREEN + "Chat");
        } else if (type.toString().toLowerCase().equalsIgnoreCase("hacking"))
        {
            meta.setDisplayName(ChatColor.GRAY + "Type: " + ChatColor.GREEN + "Cheating");
        } else if (type.toString().toLowerCase().equalsIgnoreCase("gameplay"))
        {
            meta.setDisplayName(ChatColor.GRAY + "Type: " + ChatColor.GREEN + "Gameplay");
        }

        lore.add(ChatColor.GRAY + "Player UUID: " + ChatColor.GREEN + UUID);
        lore.add(ChatColor.GRAY + "Reason: " + ChatColor.GREEN + reason);

        if (severity.toString().equalsIgnoreCase("SEV_1"))
        {
            lore.add(ChatColor.GRAY + "Severity: " + ChatColor.GREEN + "Severity One");
        } else if (severity.toString().equalsIgnoreCase("SEV_2"))
        {
            lore.add(ChatColor.GRAY + "Severity: " + ChatColor.GREEN + "Severity Two");
        } else if (severity.toString().equalsIgnoreCase("SEV_3"))
        {
            lore.add(ChatColor.GRAY + "Severity: " + ChatColor.GREEN + "Severity Three");
        } else if (severity.toString().equalsIgnoreCase("SEV_4"))
        {
            lore.add(ChatColor.GRAY + "Severity: " + ChatColor.GREEN + "Permanent");
        } else
        {
            lore.add(ChatColor.GRAY + "Severity: " + ChatColor.GREEN + "Severity Four");
        }
        lore.add(ChatColor.GRAY + "Admin: " + ChatColor.GREEN + this.staffname);
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Time Punished: " + ChatColor.GREEN + this.start);
        if (this.Activated == true)
        {

            if (this.severity.equals(PunishmentSev.SEV_4))
            {
                lore.add(ChatColor.GRAY + "Ends: " + ChatColor.GREEN + "Never");
            } else
            {
                lore.add(ChatColor.GRAY + "Ends: " + ChatColor.GREEN + end.toString());
            }
        }

        if (this.removeReason != null)
        {
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Remove Reason: " + ChatColor.GREEN + this.removeReason);
        }
        if (this.removeStaffName != null)
            lore.add(ChatColor.GRAY + "Removed By: " + ChatColor.GREEN + this.removeStaffName);

        lore.add(ChatColor.GRAY + "ID:");
        lore.add(id + "");
        meta.setLore(lore);
        i.setItemMeta(meta);

        if (this.Activated == true)
            i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return i;
    }

    public PunishmentType getType()
    {
        return type;
    }

    public String getReason()
    {
        return reason;
    }

    public String getStaffName()
    {
        return staffname;
    }

    public String getTimePunishedFor()
    {
        Date started = new Date();
        Date ends = new Date(end.getTime());

        long diff = ends.getTime() - started.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((started.getTime() - ends
                .getTime()) / (1000 * 60 * 60 * 24));

        if (this.severity.equals(PunishmentSev.SEV_4))
            return "Permanent";

        if (diffInDays > 0)
            return "" + diffInDays + " Day(s) and " + diffHours + ChatColor.GRAY + " Hour(s)";
        if (diffHours > 0)
            return "" + diffHours + " Hour(s) and " + diffMinutes + " Minutes";
        if (diffMinutes > 0)
            return "" + diffMinutes + " Minutes";
        if (diffSeconds > 0)
            return "" + diffSeconds + " Seconds";
        return "";
    }
}
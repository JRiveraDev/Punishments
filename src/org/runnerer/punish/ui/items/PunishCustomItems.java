package org.runnerer.punish.ui.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PunishCustomItems
{

    /*
     * Column headers
     */

    public static ItemStack mute()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.GRAY + "Verbal abuse, spam, caps, rudeness, etc.");

        ItemStack i = createItem(Material.BOOK_AND_QUILL, 1, (byte) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Chat Offences", lore);

        return i;
    }

    public static ItemStack game()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.GRAY + "Inappropriate things, abusing bugs/maps, etc.");

        ItemStack i = createItem(Material.REDSTONE_COMPARATOR, 1, (byte) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Gameplay Offences", lore);

        return i;
    }

    public static ItemStack hacking()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.GRAY + "Xray, speed, fly, killaura, etc.");

        ItemStack i = createItem(Material.GOLD_SWORD, 1, (byte) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Hacking Offences", lore);

        return i;
    }

    public static ItemStack devalert()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.GRAY + "Developers should not punish players without permission.");
        lore.add(ChatColor.GRAY + "");
        lore.add(ChatColor.GRAY + "Please don't punish any players! It's against the rules.");
        ItemStack i = createItem(Material.YELLOW_FLOWER, 1, (byte) 0, ChatColor.YELLOW + "" + ChatColor.BOLD + "Developer Alert", lore);

        return i;
    }
    
    /*
     * Sev dyes
     */

    public static ItemStack mute_sev1()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Mute Duration: " + ChatColor.YELLOW + "2.00 Hours");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Light Spamming");
        lore.add(ChatColor.WHITE + "Spamming the same message two to six times.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Light Advertising");
        lore.add(ChatColor.WHITE + "Advertising a server by only a name.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Trolling");
        lore.add(ChatColor.WHITE + "Trolling players while in gameplay.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Caps");
        lore.add(ChatColor.WHITE + "Using excessive caps.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        @SuppressWarnings("deprecation")
        ItemStack i = createItem(Material
                .getMaterial(351), 1, (byte) 2, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 1", lore);

        return i;
    }

    public static ItemStack mute_sev2()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Mute Duration: " + ChatColor.YELLOW + "1.00 Day");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Medium Spamming");
        lore.add(ChatColor.WHITE + "Spamming 8 to 16 times.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Abusive Behavior");
        lore.add(ChatColor.WHITE + "Abusing someone.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Light Discrimination");
        lore.add(ChatColor.WHITE + "Discriminating, in a way where it isn�t as abusive.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        ItemStack i = createItem(Material.INK_SACK, 1, (byte) 11, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 2", lore);

        return i;
    }

    public static ItemStack mute_sev3()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Mute Duration: " + ChatColor.YELLOW + "30.00 Days");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Disrespecting Staff");
        lore.add(ChatColor.WHITE + "Disrespecting staff members.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "High Advertising");
        lore.add(ChatColor.WHITE + "Recruiting players to join a server by name with disrespect towards Reinforced.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "High Spam");
        lore.add(ChatColor.WHITE + "Doing spam characters 16+ times");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        @SuppressWarnings("deprecation")
        ItemStack i = createItem(Material
                .getMaterial(351), 1, (byte) 1, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 3", lore);

        return i;
    }

    public static ItemStack mute_sev4()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Mute Duration: " + ChatColor.YELLOW + "Permanent");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Leaking Personal Info");
        lore.add(ChatColor.WHITE + "Leaking personal info. If it looks real, still punish.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Severe Advertising");
        lore.add(ChatColor.WHITE + "Recruiting players to join a server by name and IP WITH disrespect towards Reinforced.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Threats");
        lore.add(ChatColor.WHITE + "Making threats to other players/servers.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Excessive Slandering");
        lore.add(ChatColor.WHITE + "Slandering other players/the server.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Discrimination");
        lore.add(ChatColor.WHITE + "Discriminating others.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");
        lore.add(ChatColor.DARK_GREEN + "Must provide detailed reason.");

        ItemStack i = createItem(Material.BOOK, 1, (byte) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Permanent Mute", lore);

        return i;
    }

    public static ItemStack game_sev1()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Ban Duration: " + ChatColor.YELLOW + "7.00 Days");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Inappropriate Username/Skin/Cape");
        lore.add(ChatColor.WHITE + "Use a permanent ban if they have done it before.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Glitch/Bug Abuse");
        lore.add(ChatColor.WHITE + "Abusing bugs that were not intended for use.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Crossteaming");
        lore.add(ChatColor.WHITE + "Teaming with other players.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Team Trolling");
        lore.add(ChatColor.WHITE + "Making your team die, because of your actions.");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Map Abuse");
        lore.add(ChatColor.WHITE + "Abusing the map that makes an unfair advantage for you over others..");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        @SuppressWarnings("deprecation")
        ItemStack i = createItem(Material
                .getMaterial(351), 1, (byte) 2, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 1", lore);

        return i;
    }

    public static ItemStack hacking_sev1()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Ban Duration: " + ChatColor.YELLOW + "7.00 Days");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Unallowed Mods:");
        lore.add(ChatColor.WHITE + "Damage Indicators");
        lore.add(ChatColor.WHITE + "Player Trackers");
        lore.add(ChatColor.WHITE + "Minimaps of Any Kind");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        @SuppressWarnings("deprecation")
        ItemStack i = createItem(Material
                .getMaterial(351), 1, (byte) 2, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 1", lore);

        return i;
    }

    public static ItemStack hacking_sev2()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Ban Duration: " + ChatColor.YELLOW + "30.00 Days");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Unallowed Hacks:");
        lore.add(ChatColor.WHITE + "Killaura");
        lore.add(ChatColor.WHITE + "Criticals");
        lore.add(ChatColor.WHITE + "Reach");
        lore.add(ChatColor.WHITE + "Forcefield");
        lore.add(ChatColor.WHITE + "Other Non-Movement Hacks");
        ;
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        ItemStack i = createItem(Material.INK_SACK, 1, (byte) 11, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 2", lore);

        return i;
    }

    public static ItemStack hacking_sev3()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Ban Duration: " + ChatColor.YELLOW + "30.00 Days");
        lore.add(ChatColor.LIGHT_PURPLE + " ");
        lore.add(ChatColor.GRAY + "Unallowed Hacks:");
        lore.add(ChatColor.WHITE + "Speed");
        lore.add(ChatColor.WHITE + "Fly");
        lore.add(ChatColor.WHITE + "Step");
        lore.add(ChatColor.WHITE + "Spider");
        lore.add(ChatColor.WHITE + "Other Movement Hacks");
        ;
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");

        @SuppressWarnings("deprecation")
        ItemStack i = createItem(Material
                .getMaterial(351), 1, (byte) 1, ChatColor.GREEN + "" + ChatColor.BOLD + "Severity 3", lore);

        return i;
    }

    public static ItemStack hacking_sev4()
    {
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.WHITE + "Ban Duration: " + ChatColor.YELLOW + "Permanent");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.DARK_GREEN + "More in Punishment Guidelines!");
        lore.add(ChatColor.DARK_GREEN + "Must provide detailed reason.");

        ItemStack i = createItem(Material.REDSTONE_BLOCK, 1, (byte) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Permanent Ban", lore);

        return i;
    }

    /*
     * Other
     */

    public static ItemStack createItem(Material m, int amount, byte data, String name, List<String> lore)
    {
        ItemStack i = new ItemStack(m, amount, data);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(name);

        if (lore != null)
            im.setLore(lore);

        i.setItemMeta(im);

        return i;
    }

}

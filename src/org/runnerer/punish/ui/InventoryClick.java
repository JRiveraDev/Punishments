package org.runnerer.punish.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.runnerer.punish.Punishment;
import org.runnerer.punish.PunishmentSev;
import org.runnerer.punish.PunishmentSystem;
import org.runnerer.punish.PunishmentType;
import org.runnerer.punish.ui.items.PunishCustomItems;
import org.runnerer.punish.utils.F;

import java.util.ArrayList;

public class InventoryClick implements Listener
{

    public static ArrayList<String> pet = new ArrayList<String>();
    public static String Reason;
    ArrayList<String> particles = new ArrayList<String>();
    ArrayList<String> morph = new ArrayList<String>();
    ArrayList<String> emote = new ArrayList<String>();
    ArrayList<String> mount = new ArrayList<String>();
    ArrayList<String> gadget = new ArrayList<String>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        // Check the person is in a account GUI.

        Player player = (Player) e.getWhoClicked();

        if (PunishGUIManager.contains(e.getWhoClicked()
                .getName()))
        {
            PunishGUI agui = PunishGUIManager.getGUI(e
                    .getWhoClicked().getName());


            if (e.getInventory().equals(agui.PunishmentGUI) || e.getInventory()
                    .equals(agui.TraineePunishmentGUI) || e.getInventory().equals(agui.DevPunishmentGUI))
            {
                PunishGUIManager.getGUI(player.getName())
                        .justOpened();

                e.setCancelled(true);
                e.setCursor(new ItemStack(Material.AIR));

                PunishGUI account = PunishGUIManager.getGUI(e
                        .getWhoClicked().getName());
                String topunish = account.AccountsPlayerName;

                if (e.getCurrentItem() == null || e.getCurrentItem()
                        .getType() == Material.AIR)
                    return;
                String displayName = Reason;
                if (e.getCurrentItem().equals(PunishCustomItems.mute_sev1()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.CHAT, PunishmentSev.SEV_1, account.staffname);
                    p.setReason(displayName);
                    p.activate();
                }
                if (e.getCurrentItem().equals(PunishCustomItems.mute_sev2()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.CHAT, PunishmentSev.SEV_2, account.staffname);
                    p.setReason(displayName);
                    p.activate();

                }
                if (e.getCurrentItem().equals(PunishCustomItems.mute_sev3()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.CHAT, PunishmentSev.SEV_3, account.staffname);
                    p.setReason(displayName);
                    p.activate();

                }
                if (e.getCurrentItem().equals(PunishCustomItems.mute_sev4()))
                {
                	
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.CHAT, PunishmentSev.SEV_4, account.staffname);
                    p.setReason(displayName);
                    p.activate();

                }

                if (e.getCurrentItem().equals(PunishCustomItems.game_sev1()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.GAMEPLAY, PunishmentSev.SEV_1, account.staffname);
                    p.setReason(displayName);
                    p.activate();
                }

                if (e.getCurrentItem().equals(PunishCustomItems.hacking_sev1()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.HACKING, PunishmentSev.SEV_1, account.staffname);
                    p.setReason(displayName);
                    p.activate();
                }
                if (e.getCurrentItem().equals(PunishCustomItems.hacking_sev2()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.HACKING, PunishmentSev.SEV_2, account.staffname);
                    p.setReason(displayName);
                    p.activate();
                }
                if (e.getCurrentItem().equals(PunishCustomItems.hacking_sev3()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.HACKING, PunishmentSev.SEV_3, account.staffname);
                    p.setReason(displayName);
                    p.activate();
                }
                if (e.getCurrentItem().equals(PunishCustomItems.hacking_sev4()))
                {
                    e.getWhoClicked().closeInventory();
                    Punishment p = new Punishment(topunish, PunishmentType.HACKING, PunishmentSev.SEV_4, account.staffname);
                    p.setReason(displayName);
                    p.activate();

                }

                if (!(e.getCurrentItem().getEnchantments().isEmpty()))
                {
                    if (account.staffname
                            .equalsIgnoreCase(account.AccountsPlayerName))
                    {
                            ((Player) e.getWhoClicked()).sendMessage(F
                                    .main("Punish", "You cannot nullify your own punishments!"));

                            e.getWhoClicked().closeInventory();
                            return;

                    }

                    String ID = e.getCurrentItem().getItemMeta().getLore()
                            .get(8);
                    ChatColor.stripColor(ID);
                    int id = 0;
                    try
                    {
                        id = Integer.parseInt(ID);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }


                    final Player pl = (Player) e
                            .getWhoClicked();

                    pl.closeInventory();

                    e.getCursor().setType(Material.AIR);
                    e.setCancelled(true);
                    PunishmentSystem.removePunishment(id, false, displayName, e.getWhoClicked().getName());

                    ((Player) e.getWhoClicked())
                            .sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You removed " + topunish + "'s punishment for " + displayName + ".");
                    for (Player p : Bukkit.getOnlinePlayers())
                    {
                        if (p.hasPermission("punish.mod"))
                        {
                            if (Bukkit.getPlayer(e
                                    .getWhoClicked()
                                    .getName()) != p)
                            {
                                p.sendMessage(F
                                        .main("Punish Log", e
                                                .getWhoClicked()
                                                .getName() + " removed a punishment from " + account.AccountsPlayerName + " for " + displayName + "."));
                            }
                        }
                    }
                    return;

                }

                return;
            }
        }

    }

}

package org.runnerer.punish.ui;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InvenClose implements Listener
{

    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        if (PunishGUIManager.contains(e.getPlayer().getName()))
        {
            PunishGUIManager.getGUI(e.getPlayer().getName())
                    .justClosed();
        }

        if (e.getInventory().getType() == InventoryType.ANVIL)
            e.getInventory().remove(new ItemStack(Material.PAPER));

        e.getPlayer().getInventory().remove(new ItemStack(Material.PAPER));
    }
}

package org.runnerer.punish.ui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PunishGUIManager
{

    /**
     * String: Players name AccountGUI instance.
     */
    public static Map<String, PunishGUI> openGUIs = new HashMap<String, PunishGUI>();

    public PunishGUIManager()
    {
    }

    public static PunishGUI getGUI(String whoclicked)
    {
        return openGUIs.get(whoclicked);
    }

    public static boolean contains(String staffName)
    {
        return openGUIs.containsKey(staffName);
    }

    public static void removeGUI(String staffName)
    {
        openGUIs.remove(staffName);
    }

    public static void createPunish(Player staff, String accountName, String id)
    {
        PunishGUI agui = new PunishGUI(accountName, staff.getName(), id);

        openGUIs.put(staff.getName(), agui);
        
        if (staff.hasPermission("punish.dev"))
        {
        	staff.openInventory(agui.DevPunishmentGUI);
        	return;
        }
        if (staff.hasPermission("punish.mod"))
        {
            staff.openInventory(agui.PunishmentGUI);
        } else
        {
            staff.openInventory(agui.TraineePunishmentGUI);
        }
    }

}

package org.runnerer.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.runnerer.punish.command.HistoryCommand;
import org.runnerer.punish.command.NetworkKickCommand;
import org.runnerer.punish.command.PunishCommand;
import org.runnerer.punish.database.MySQL;
import org.runnerer.punish.events.Chat;
import org.runnerer.punish.events.Login;
import org.runnerer.punish.utils.F;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PunishmentSystem extends JavaPlugin
{

    public static PunishmentSystem instance;

    @Override
    public void onEnable()
    {
        instance = this;
        addCommands();

        new MySQL();
        try
        {
            MySQL.Instance.openConnection();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void punishPlayer(String playerUUID, String player, String staffName, PunishmentSev sev, PunishmentType type, String reason)
    {

        Timestamp end = new Timestamp(System.currentTimeMillis());

        int pType = 1;
        int severity = 0;

        switch (sev)
        {
            case SEV_1:
                severity = 1;
                if (type.equals(PunishmentType.CHAT))
                {
                    end.setMinutes(end.getMinutes() + 60 * 2);

                    pType = 1;
                } else if (type.equals(PunishmentType.GAMEPLAY))
                {
                    end.setHours(end.getHours() + 24 * 7);

                    pType = 2;
                } else if (type.equals(PunishmentType.HACKING))
                {
                    end.setHours(end.getHours() + 24 * 7);

                    pType = 3;
                }
                break;
            case SEV_2:
                severity = 2;
                if (type.equals(PunishmentType.CHAT))
                {
                    end.setHours(end.getHours() + 24);

                    pType = 1;
                } else if (type.equals(PunishmentType.HACKING))
                {
                    end.setHours(end.getHours() + 24 * 30);

                    pType = 3;
                }
                break;
            case SEV_3:
                severity = 3;
                if (type.equals(PunishmentType.CHAT))
                {
                    end.setHours(end.getHours() + 24);

                    pType = 1;
                } else if (type.equals(PunishmentType.HACKING))
                {
                    end.setHours(end.getHours() + 24 * 30);

                    pType = 3;
                }
                break;
            case SEV_4:
                severity = 4;
                if (type.equals(PunishmentType.CHAT))
                {
                    pType = 1;
                } else if (type.equals(PunishmentType.HACKING))
                {
                    pType = 3;
                }
                break;
        }

        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM ipinfoPunishments WHERE uuid = '" + playerUUID + "';");
            ResultSet res2 = MySQL
                    .querySQL("SELECT * FROM ipinfoPunishments WHERE uuid = '" + Bukkit.getPlayer(staffName).getUniqueId().toString() + "';");
            if (staffName.equalsIgnoreCase("SAPLING"))
            {
                res.next();
                String ipPunished = res.getString(2);
                MySQL
                        .updateSQL("INSERT INTO Punishment (`ID`, `UUID`, `PlayerName`, `StaffName`, `Sev`, `pType`, `Reason`, `Activated`, `Ends`, `Active`, `RemoveReason`, `RemovedBy`, `ipPunisher`, `ipPunished`) VALUES (NULL, '" + playerUUID + "', '" + player + "', '" + staffName + "' , '" + severity + "', '" + pType + "', '" + reason + "', CURRENT_TIMESTAMP,'" + end + "', '1', NULL, NULL, '" + "SAPLING" + "', '" + ipPunished + "' );");
                System.out
                        .println("Punish> " + staffName + " punished " + player + ".");
                return;
            }
            res2.next();
            String ip = res2.getString(2);

            res.next();
            String ipPunished = res.getString(2);
            MySQL
                    .updateSQL("INSERT INTO Punishment (`ID`, `UUID`, `PlayerName`, `StaffName`, `Sev`, `pType`, `Reason`, `Activated`, `Ends`, `Active`, `RemoveReason`, `RemovedBy`, `ipPunisher`, `ipPunished`) VALUES (NULL, '" + playerUUID + "', '" + player + "', '" + staffName + "' , '" + severity + "', '" + pType + "', '" + reason + "', CURRENT_TIMESTAMP,'" + end + "', '1', NULL, NULL, '" + ip + "', '" + ipPunished + "' );");
            System.out
                    .println("Punish> " + staffName + " punished " + player + ".");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            Bukkit.getPlayer(staffName).sendMessage(F
                    .main("Punish", "Error while getting player IP."));
            return;
        }
        if (type.equals(PunishmentType.HACKING))
        {
            if (!(Bukkit.getPlayer(player) == null))
            {
                Bukkit.getPlayer(player)
                        .kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You have been suspended from Reinforced. "

                                + ChatColor.GRAY + "" + ChatColor.WHITE + "\n" + reason + "\n" + ChatColor.RED + "\n Appeal at " + ChatColor.RED + "www.reinforcedmc.com");
            }
        }
        if (Bukkit.getPlayer(staffName) == null)
            return;
        Bukkit.getPlayer(staffName)
                .sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You punished " + player + " for " + reason + ".");
    }

    public static PastPunishment getPunishment(int ID)
    {
        try
        {

            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE ID = '" + ID + "';");
            res.next();
            int pType = 0;
            try
            {
                pType = res.getInt("pType");
            }
            catch (Exception e)
            {

            }
            PunishmentType type = null;
            switch (pType)
            {
                case 1:
                    type = PunishmentType.CHAT;
                    break;
                case 2:
                    type = PunishmentType.GAMEPLAY;
                    break;
                case 3:
                    type = PunishmentType.HACKING;
                    break;
            }

            try
            {
                String staff = res.getString("StaffName");
                String reason = res.getString("Reason");
                String remove = res.getString("RemoveReason");
                String removedby = res.getString("RemovedBy");

                boolean activate;
                if (res.getInt("Active") == 1)
                    activate = true;
                else
                {
                    activate = false;
                }

                int sev = res.getInt("Sev");

                PunishmentSev severity = null;

                switch (sev)
                {
                    case 1:
                        severity = PunishmentSev.SEV_1;
                        break;
                    case 2:
                        severity = PunishmentSev.SEV_2;
                        break;
                    case 3:
                        severity = PunishmentSev.SEV_3;
                        break;
                    case 4:
                        severity = PunishmentSev.SEV_4;
                        break;
                    default:
                        severity = PunishmentSev.SEV_1;
                        break;
                }
                Timestamp start = res.getTimestamp("Activated");

                Timestamp end = res.getTimestamp("Ends");
                String player = res.getString("UUID");

                PastPunishment p = new PastPunishment(ID, player, type, reason, staff, remove, removedby, activate, start, end, severity);
                return p;
            }
            catch (SQLException e)
            {

            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean hasBeenPunished(String UUID)
    {
        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE UUID = '" + UUID + "';");
            if (res.next())
                return true;
            return false;

        }
        catch (ClassNotFoundException | SQLException e)
        {
            return false;
        }
    }

    public static List<PastPunishment> getAllPunishments(int ID)
    {
        List<PastPunishment> pp = new ArrayList<>();
        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE ID = '" + ID + "';");
            res.next();
            pp.add(PunishmentSystem.getPunishment(res.getInt("ID")));
            while (res.next())
            {
                pp.add(PunishmentSystem.getPunishment(res.getInt("ID")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pp;
    }

    public static List<PastPunishment> getAllPunishmentsFromUUID(String UUID)
    {
        List<PastPunishment> pp = new ArrayList<>();

        try
        {

            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE UUID='" + UUID + "';");
            res.next();
            int id = 0;
            try
            {
                id = res.getInt("ID");
            }
            catch (Exception e)
            {

            }
            pp.add(getPunishment(id));

            while (res.next())
            {
                pp.add(getPunishment(res.getInt("ID")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pp;
    }

    public static List<PastPunishment> getAllPunishmentsFromPunishedIP(String ip)
    {
        List<PastPunishment> pp = new ArrayList<>();

        try
        {

            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE ipPunished='" + ip + "';");
            res.next();
            int id = 0;
            try
            {
                id = res.getInt("ID");
            }
            catch (Exception e)
            {

            }
            pp.add(getPunishment(id));

            while (res.next())
            {
                pp.add(getPunishment(res.getInt("ID")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pp;
    }

    public static List<PastPunishment> getAllPunishmentsFromPunisherIP(String ip)
    {
        List<PastPunishment> pp = new ArrayList<>();

        try
        {

            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE ipPunisher='" + ip + "';");
            res.next();
            int id = 0;
            try
            {
                id = res.getInt("ID");
            }
            catch (Exception e)
            {

            }
            pp.add(getPunishment(id));

            while (res.next())
            {
                pp.add(getPunishment(res.getInt("ID")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pp;
    }

    public static void removePunishment(int id, boolean natural, String reason, String staff)
    {
        try
        {
            int not = 0;
            MySQL
                    .updateSQL("UPDATE Punishment SET Active='" + not + "' WHERE ID='" + id + "';");

            if (natural == false)
            {
                MySQL
                        .updateSQL("UPDATE Punishment SET RemoveReason ='" + reason + "' WHERE ID='" + id + "';");
                MySQL
                        .updateSQL("UPDATE Punishment SET RemovedBy ='" + staff + "' WHERE ID='" + id + "';");
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isActive(int id)
    {
        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE ID = '" + id + "';");
            res.next();
            for (PastPunishment p : getAllPunishments(res.getInt("ID")))
            {
                p.hasExpired();
            }

            int active = res.getInt("Active");

            if (active == 1)
                return true;

            if (active == 0)
                return false;

        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isMuted(String UUID)
    {
        if (getAllPunishmentsFromUUID(UUID).equals(null))
            return false;

        for (PastPunishment m : getAllPunishmentsFromUUID(UUID))
        {
            try
            {
                if (m.isActive() && m.getType() == PunishmentType.CHAT)
                {
                    return true;
                }
            }
            catch (Exception e)
            {
                return false;
            }
        }
        return false;
    }

    public static boolean isBanned(String UUID)
    {
        for (PastPunishment m : PunishmentSystem.getAllPunishmentsFromUUID(UUID))
        {
            if (m.isActive() && m.getType() == PunishmentType.HACKING || m
                    .getType() == PunishmentType.GAMEPLAY)
            {
                return true;
            }
        }
        return false;
    }

    public static PastPunishment getCurrentMute(String UUID)
    {
        PastPunishment recent = null;


        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE UUID = '" + UUID + "';");
            while (res.next())
            {
                if (res.getInt("pType") == 1 && isActive(res.getInt("ID")))
                    recent = PunishmentSystem.getPunishment(res.getInt("ID"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return recent;
    }

    public static PastPunishment getCurrentBan(String UUID)
    {
        PastPunishment recent = null;


        try
        {
            ResultSet res = MySQL
                    .querySQL("SELECT * FROM Punishment WHERE UUID = '" + UUID + "';");
            while (res.next())
            {
                if (res.getInt("pType") == 2 || res.getInt("pType") == 3 && isActive(res.getInt("ID")))
                    recent = PunishmentSystem.getPunishment(res.getInt("ID"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            return null;
        }

        return recent;
    }

    public void addCommands()
    {
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("networkkick").setExecutor(new NetworkKickCommand());
        getCommand("history").setExecutor(new HistoryCommand());


        Bukkit.getServer().getPluginManager()
                .registerEvents(new Login(), this);


        Bukkit.getServer().getPluginManager()
                .registerEvents(new Chat(), this);
    }
}
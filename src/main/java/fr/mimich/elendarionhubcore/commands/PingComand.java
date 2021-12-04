package fr.mimich.elendarionhubcore.commands;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class PingComand implements CommandExecutor {

    private ElendarionHubCore main;

    public PingComand(ElendarionHubCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "ping", Collections.singletonMap("%ping%", Integer.toString(((CraftPlayer) commandSender).getHandle().ping))));
        } else {
            commandSender.sendMessage(this.main.getConfig().getString("not-player"));
        }
        return true;
    }
}

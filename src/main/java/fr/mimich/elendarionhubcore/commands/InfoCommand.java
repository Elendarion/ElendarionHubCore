package fr.mimich.elendarionhubcore.commands;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandExecutor {

    private ElendarionHubCore main;

    public InfoCommand(ElendarionHubCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            this.main.getInfoInventory().open((Player) commandSender);
        } else {
            commandSender.sendMessage(this.main.getConfig().getString("not-player"));
        }
        return true;
    }
}

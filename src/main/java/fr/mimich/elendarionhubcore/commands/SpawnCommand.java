package fr.mimich.elendarionhubcore.commands;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.SuperConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private ElendarionHubCore main;

    public SpawnCommand(ElendarionHubCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            this.main.getSpawn().teleportSpawn(player);
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "spawn-teleport"));
        } else {
            commandSender.sendMessage(this.main.getConfig().getString("not-player"));
        }
        return true;
    }
}

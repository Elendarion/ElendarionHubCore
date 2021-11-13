package fr.mimich.elendarionhubcore.commands;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.SuperConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CustomHelpCommand implements CommandExecutor {

    private ElendarionHubCore main;

    public CustomHelpCommand(ElendarionHubCore main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final String JUMPER = "\n";
        Map<String, String> placeHolders = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Command c : this.main.getCustomHelp().getCommands()) {
            placeHolders.put("%usage%", c.getUsage());
            placeHolders.put("%description%", c.getDescription());
            if (commandSender.hasPermission(c.getPermission()) || commandSender.isOp()) {
                stringBuilder.append(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.HELP, "format", placeHolders) + JUMPER);
            }
            placeHolders.clear();
        }
        commandSender.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.HELP, "top") + JUMPER + stringBuilder + this.main.getSuperConfig().getConfigText(SuperConfig.TextType.HELP, "bottom"));
        return true;
    }
}

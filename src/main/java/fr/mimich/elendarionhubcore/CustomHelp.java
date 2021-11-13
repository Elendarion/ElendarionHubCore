package fr.mimich.elendarionhubcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class CustomHelp {

    private ElendarionHubCore main;

    private Set<Command> commands;

    public CustomHelp(ElendarionHubCore main) {
        this.main = main;
        this.commands = new HashSet<>();
        this.init();
    }

    private void init() {

        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {

            if (plugin.getName().contains("Elendarion")) {
                this.commands.addAll(PluginCommandYamlParser.parse(plugin));
            }
        }
    }

    public Set<Command> getCommands() {
        return commands;
    }
}

package fr.mimich.elendarionhubcore;

import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class NPC {

    private ElendarionHubCore main;

    private List<net.jitse.npclib.api.NPC> npcList;

    private Map<net.jitse.npclib.api.NPC, String> npcServers;

    public NPC(ElendarionHubCore main) {
        this.main = main;
        this.npcList = new ArrayList<>();
        this.npcServers = new HashMap<>();
    }

    public void loadNpc() {
        String base = SuperConfig.TextType.NPC.name().toLowerCase();
        FileConfiguration configuration = this.main.getConfig();
        Set<String> npcNames = new HashSet<>();
        configuration.getConfigurationSection(base).getKeys(true).forEach(keys -> npcNames.add(keys.split("\\.")[0]));
        String npcConfigAddress;
        String npcConfigLocationAddress;
        String npcConfigLookAtLocationAddress;
        String serverName;
        Location npcLocation;
        Location lookAtLocation;
        net.jitse.npclib.api.NPC npc;
        for (String npcName : npcNames) {
            npcConfigAddress = base + "." + npcName + ".";
            npcConfigLocationAddress = npcConfigAddress + "location.";
            npcConfigLookAtLocationAddress = npcConfigAddress + "look-at";
            serverName = this.main.getConfig().getString(npcConfigAddress + "server-name");
            npcLocation = new Location(Bukkit.getWorld("world"), configuration.getDouble(npcConfigLocationAddress + "x"), configuration.getDouble(npcConfigLocationAddress + "y"), configuration.getDouble(npcConfigLocationAddress + "z"));

            if (this.main.getConfig().getString(npcConfigLookAtLocationAddress).equalsIgnoreCase("spawn")) {
                lookAtLocation = Bukkit.getWorld("world").getSpawnLocation();
            } else {
                npcConfigLookAtLocationAddress = npcConfigLocationAddress + ".";
                lookAtLocation = new Location(Bukkit.getWorld("world"), configuration.getDouble(npcConfigLookAtLocationAddress + "x"), configuration.getDouble(npcConfigLookAtLocationAddress + "y"), configuration.getDouble(npcConfigLookAtLocationAddress + "z"));
            }
            npc = this.main.getNpcLib().createNPC(this.main.getSuperConfig().getConfigTextList(SuperConfig.TextType.NPC, npcName + ".lines", Collections.singletonMap("%server-name%", serverName)));
            npc.setLocation(npcLocation);
            npc.lookAt(lookAtLocation);
            npc.setSkin(new Skin(this.main.getConfig().getString(npcConfigAddress + "skin"), ""));
            npc.create();
            this.npcList.add(npc);

            if (this.main.getConfig().getBoolean(npcConfigAddress + "server-teleport"))
                this.npcServers.putIfAbsent(npc, serverName);
        }
    }

    public List<net.jitse.npclib.api.NPC> getNpcList() {
        return npcList;
    }

    public Map<net.jitse.npclib.api.NPC, String> getNpcServers() {
        return npcServers;
    }
}
